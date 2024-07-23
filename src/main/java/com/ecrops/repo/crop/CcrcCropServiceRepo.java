package com.ecrops.repo.crop;

import com.ecrops.dto.crop.response.CCRCLandDetails;
import com.ecrops.dto.webland.CCRCCropData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
@Transactional

public class CcrcCropServiceRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public int checkRecordIsAvailableInDatabase(int villageCode, String season, int cropYear) {
		try {

			String downtab = "ecrop" + cropYear + "." + "verify_datadownload";
			String QRY_GET_RECORDS_CNT = "select ccrc_cnt as count from " + downtab
					+ " where  cr_vcode=? and cr_year=? and cr_season=?";

			Query query = entityManager.createNativeQuery(QRY_GET_RECORDS_CNT);
			query.setParameter(1, villageCode);
			query.setParameter(2, cropYear);
			query.setParameter(3, season);

			Number result = (Number) query.getSingleResult();
			int recordCount = (result != null) ? result.intValue() : 0;

			System.out.println("Record count found: " + recordCount);

			return recordCount;
		} catch (NoResultException e) {
			System.out.println("No records found for the specified criteria.");
			return 0;
		}
	}

	@Transactional
	public int checkInsertAndViewCccrcCropDetails(int activeYear, String userId, String ipAddress, int wDCode,
			int villageCode, int wMCode, String season, int cropYear, int mandalCode, int districtCode)
			throws Exception {
		int bookIns = 0;
		int cdowndet = 0, diffIns = 0, totccrc = 0;
		int existingCnt = 0;

		String ccrcdetTab = "ccrc_details";
		String ccrcTempTab = "ccrc_details_temp";
		// String cccrdownloadTab = "ccrcdownloaddetails";
		String downtab = "ecrop" + cropYear + "." + "verify_datadownload";

		String pattmast = "pattadarmast_wb_partition_"; // pattadarmast_wb_partition_k232023

		String dcode = Integer.toString(wDCode);
		String cropyear = Integer.toString(cropYear);
		String activeyear = Integer.toString(activeYear);

		if (dcode.length() == 1) {
			dcode = "0" + dcode;

		}
		if (Integer.parseInt(activeyear.trim()) == Integer.parseInt(cropyear.trim())) {
			ccrcTempTab = "ecrop" + activeYear + "." + ccrcTempTab;
			ccrcdetTab = "ecrop" + activeYear + "." + ccrcdetTab;
			// cccrdownloadTab = "ecrop" + activeYear + "." + cccrdownloadTab;
			pattmast = "ecrop" + activeYear + "." + pattmast + season + dcode + cropYear;
		}

		int alreadyRecordsCount = checkRecordIsAvailableInDatabase(villageCode, season, cropYear);
		if (alreadyRecordsCount <= 0) {

			int ccrcIns = insertCropDetailsFromWebland(dcode, villageCode, wMCode, activeYear, season, cropYear,
					districtCode, mandalCode);
			System.out.println("ccrcdetTab---------->" + ccrcdetTab);
			System.out.println("ccrcIns------->" + ccrcIns);

			

				String QRY_INS_RECORDS_INT = "", partkey = "";
				String wbdist = String.valueOf(wDCode);
				if (wbdist.length() == 1) {
					wbdist = "0" + wbdist;
				}
				String crbooking = "cr_booking_nwb";
				if (activeyear.equals(cropyear)) {
					crbooking = "ecrop" + activeYear + "." + crbooking;

				}
				System.out.println("crbooking---------->" + crbooking);
				if (ccrcIns > 0) {

					QRY_INS_RECORDS_INT = "INSERT INTO " + ccrcdetTab
							+ "( ccrcid, docid, wbdcode, dname, wbmcode, mname, vcode, vname, tenantfarmername, rid, khatano, surveyno, extent, "
							+ "enrolldate, enrollenddate, gender, dt_crt, caste, cr_year, cr_season, cultivable_land, uncultivable_land, tot_extent, pname, pfname, anubhavadar_extent, part_key, dcode, mcode, dtcreated, tenantfarmer_fathername, tenant_aadhaar, land_nature, "
							+ "anubhavadar_name, anubhavadar_fathername, cr_wsno,regno,sjointoccupant,owner_aadhaar) "
							+ "SELECT ccrcid, docid, wbdcode, dname, wbmcode, mname, vcode, vname, tenantfarmername, rid, khatano, surveyno, extent, enrolldate, enrollenddate, gender, dt_crt, caste, cr_year, cr_season, cultivable_land, uncultivable_land, tot_extent, pname, pfname, anubhavadar_extent, part_key, dcode, mcode, dtcreated, tenantfarmer_fathername, tenant_aadhaar, land_nature, "
							+ "anubhavadar_name, anubhavadar_fathername, cr_wsno,regno,sjointoccupant,owner_aadhaar FROM ecrop2024.ccrc_details_temp WHERE vcode=? AND (ccrcid,vcode,khatano,surveyno,extent) "
							+ "NOT IN (SELECT ccrcid,vcode,khatano,surveyno,extent FROM ecrop2024.ccrc_details WHERE vcode=?)";

					Query query = entityManager.createNativeQuery(QRY_INS_RECORDS_INT);
					query.setParameter(1, villageCode);
					query.setParameter(2, villageCode);

					int tmptoccrIns = query.executeUpdate();

					System.out.println("tmptoccrIns------->" + tmptoccrIns);

					String QRY_INS_RECORDS_INTCRB = "INSERT INTO " + crbooking
							+ "( ccrcid, cr_dist_code, cr_mand_code, cr_vcode, occupname, kh_no, cr_sno, occupant_extent,tot_extent, "
							+ "  soc_category,data_src, cr_year,cr_season,owner_tenant,cr_tr_i_ext,cr_tr_d_ext,part_key,oc_name,oc_fname,dcode,"
							+ " mcode,occupfname,cr_farmeruid,anubhavadar_name,cultivator_type,cr_wsno,regno,sjointoccupant)  "
							+ " select ccrcid,wbdcode, wbmcode,  vcode, tenantfarmername, khatano, surveyno, extent,tot_extent,  caste,'C',cr_year,cr_season,'T',"
							+ " 0,0,part_key,pname,pfname,dcode,mcode,tenantfarmer_fathername,tenant_aadhaar,anubhavadar_name,'C',cr_wsno,regno,sjointoccupant "
							+ "  from " + ccrcdetTab
							+ " where cr_wsno is not null and enrollenddate::::date>=now()::::date and vcode=? and cr_year=? and cr_season=?  and (CAST(ccrcid as text)||CAST(khatano as text)||CAST(surveyno as text)||CAST(vcode as text)) not in "
							+ " ( select CAST(ccrcid as text)||CAST(kh_no as text)||CAST(cr_sno as text)||CAST(cr_vcode as text ) from "
							+ crbooking + " where  data_src='C' and cr_vcode=? and cr_year=? and cr_season=? )";

					Query queryCrb = entityManager.createNativeQuery(QRY_INS_RECORDS_INTCRB);
					queryCrb.setParameter(1, villageCode);
					queryCrb.setParameter(2, cropYear);
					queryCrb.setParameter(3, season);
					queryCrb.setParameter(4, villageCode);
					queryCrb.setParameter(5, cropYear);
					queryCrb.setParameter(6, season);

					bookIns = queryCrb.executeUpdate();

					System.out.println("bookIns------->" + bookIns);

					String qry4 = "DELETE FROM " + ccrcTempTab + " WHERE vcode=? AND cr_year=? AND cr_season=? ";

					Query deleteQuery = entityManager.createNativeQuery(qry4);
					deleteQuery.setParameter(1, villageCode);
					deleteQuery.setParameter(2, cropYear);
					deleteQuery.setParameter(3, season);

					int delTemp = deleteQuery.executeUpdate();

				//	System.out.println("delTemp------->" + delTemp);

					String updateQuery = "UPDATE " + downtab + " SET ccrc_cnt = ?, ccrc = 'Y', dt_ccrc = now() "
							+ " WHERE cr_dist_code = ? AND cr_mand_code = ? AND cr_vcode = ? AND cr_year = ? AND cr_season = ?";

					Query insertCdown = entityManager.createNativeQuery(updateQuery);
					insertCdown.setParameter(1, bookIns);
					insertCdown.setParameter(2, wDCode);
					insertCdown.setParameter(3, wMCode);
					insertCdown.setParameter(4, villageCode);
					insertCdown.setParameter(5, cropYear);
					insertCdown.setParameter(6, season);
					cdowndet = insertCdown.executeUpdate();

					System.out.println(" inseted bookins count----->" + bookIns);

					System.out.println("cdowndet----->" + cdowndet);

					System.out.println("bookIns:" + bookIns);
					totccrc = existingCnt + bookIns;
					System.out.println("totccrc::" + totccrc + "--extcnt:" + existingCnt + "--diffIns:" + diffIns);

				}
			}

		

		return bookIns;
	}

	@Transactional
	public int insertCropDetailsFromWebland(String dcode, int villageCode, int wMCode, int activeYear, String season,
			int cropYear, int sesdcode, int sesmcode) {

		int ccrcIns = 0;
		String pattmast = "pattadarmast_wb_partition_"; //
		pattmast = "ecrop" + activeYear + "." + pattmast + season + dcode + cropYear;

		String ccrcTempTab = "ccrc_details_temp";
		String cropyear = Integer.toString(cropYear);

		String activeyear = Integer.toString(activeYear);

		if (dcode.length() == 1) {
			dcode = "0" + dcode;

		}
		if (Integer.parseInt(activeyear.trim()) == Integer.parseInt(cropyear.trim())) {
			ccrcTempTab = "ecrop" + activeYear + "." + ccrcTempTab;
		}

		String QRY_INS_CROP_DET = "INSERT INTO " + ccrcTempTab + " (ccrcid, docid, wbdcode, dname, wbmcode, "
				+ "mname, vcode, vname, tenantfarmername, khatano, "
				+ "surveyno, extent, enrolldate, caste, cr_year,"
				+ " cr_season, tot_extent, anubhavadar_extent, part_key,dcode,"
				+ "  mcode,tenantfarmer_fathername, tenant_aadhaar, enrollenddate, land_nature,"
				+ "gender, pname, pfname, anubhavadar_name, anubhavadar_fathername, cultivable_land, uncultivable_land, cr_wsno, regno, sjointoccupant ) "
				+ "VALUES " + "(?, ?, ?, ?, ?, " 
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, to_date(cast(? as TEXT), 'YYYY-MM-DD'), ?, ?, " 
				+ "?, ?, ?, ?, ?,"
				+ " ?, ?, ?, to_date(cast(? as TEXT), 'YYYY-MM-DD'), ?,"
				+ " ?, ?,?,?,?,?,?,?,?,1)";

		String checkresurveyQuery = "select  resurveyed as resurvey from wbvillage_mst where wbvcode=?";
		Query query1 = entityManager.createNativeQuery(checkresurveyQuery);
		query1.setParameter(1, villageCode);
		query1.setMaxResults(1);
		Object result1 = query1.getSingleResult();

		String resury = "";

			    if (result1 != null && !result1.toString().isEmpty()) {
			        resury = result1.toString();
			        System.out.println("resuryresponse---->" + resury);
			    
			if (resury.equals("Y")) {
				
		        System.out.println("villahgeresurved---->" + resury);

				List<CCRCLandDetails> crropDataNewApiList = getCcrcNewApiData(String.valueOf(villageCode));

				System.out.println("crropDataNewApiList---->"+crropDataNewApiList);
				for (CCRCLandDetails data : crropDataNewApiList) {
					try {
						// String gender = mapGender(data.getTenantGender());

						int casteCode = getCasteCode(data.getTenantCaste());
						System.out.println("casteCode--->"+casteCode);
						String anubhavadarName = mapString(data.getOccupantName());
						String anubhavadarFname = mapString(data.getOccupantFatherName());
						System.out.println("anubhavadarName--->"+anubhavadarName);
						System.out.println("anubhavadarFname--->"+anubhavadarFname);

						double weblandOccupantExt = Double.parseDouble(data.getCardExtent());
						double weblandTotalExt = Double.parseDouble(data.getExtent());
						System.out.println("weblandOccupantExt--->"+weblandOccupantExt);
						System.out.println("weblandTotalExt--->"+weblandTotalExt);

//						if (weblandOccupantExt > weblandTotalExt || weblandOccupantExt == 0 || weblandTotalExt == 0) {
//							continue;
//						}
						String doubleStringwbdcode = data.getDistrictCode(); 
						double doubleValuewbdcode = Double.parseDouble(doubleStringwbdcode);
						int districtCode = (int) doubleValuewbdcode; 
						System.out.println("districtCode--->"+districtCode);

						String doubleStringwbmcode= data.getMandalCode(); 
						double doubleValuewbmcode = Double.parseDouble(doubleStringwbmcode);
						int mandalCode = (int) doubleValuewbmcode; 
					
						System.out.println("mandalCode--->"+mandalCode);

						ccrcIns += entityManager.createNativeQuery(QRY_INS_CROP_DET)
								.setParameter(1, data.getCcrcId())
								.setParameter(2, data.getCcrcId())
								.setParameter(3, districtCode)
								.setParameter(4, data.getDistrictName())
								.setParameter(5, mandalCode)

								.setParameter(6, data.getMandalName())
								.setParameter(7, Integer.parseInt(data.getVillageCode()))
								.setParameter(8, data.getVillageName())
								.setParameter(9, data.getTenantFarmerName())
								.setParameter(10, Integer.parseInt(data.getKhataNo()))

								.setParameter(11, data.getLpmNo())
								.setParameter(12, Double.parseDouble(data.getCardExtent()))
								.setParameter(13, data.getEnrollDate())
								.setParameter(14, casteCode)
								.setParameter(15, cropYear)

								.setParameter(16, season)
								.setParameter(17, weblandTotalExt)
								.setParameter(18, weblandOccupantExt)
								.setParameter(19, season + dcode + cropYear)
								.setParameter(20, sesdcode)

								.setParameter(21, sesmcode).setParameter(22, data.getTenantFarmerFatherName())
								.setParameter(23, data.getTenantAadharNo())
								.setParameter(24, data.getEnrollEndDate())
								.setParameter(25, data.getLandNature())

								.setParameter(26, data.getTenantGender()).setParameter(27, data.getLandOwnerName())
								.setParameter(28, data.getLandOwnerFatherName()).setParameter(29, anubhavadarName)
								.setParameter(30, anubhavadarFname).setParameter(31, 0)
								.setParameter(32, 0)
								.setParameter(33, Integer.parseInt(data.getLpmNo()))
								.setParameter(34, Integer.parseInt(data.getKhataNo()))
								
								
								.executeUpdate();System.out.println("QRY_INS_CROP_DET--->"+QRY_INS_CROP_DET);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
			}
		 else {
	        System.out.println("villahgeNotresurved---->" + resury);

			String QRY_INS_CROP_DET_old = "INSERT INTO " + ccrcTempTab + " (ccrcid, docid, wbdcode, dname, wbmcode, "
					+ "mname, vcode, vname, tenantfarmername, khatano, "
					+ "surveyno, extent, enrolldate, caste, cr_year,"
					+ " cr_season, tot_extent, anubhavadar_extent, part_key,dcode,"
					+ "  mcode,tenantfarmer_fathername, tenant_aadhaar, enrollenddate, land_nature,"
					+ "gender, pname, pfname, anubhavadar_name, anubhavadar_fathername, cultivable_land, uncultivable_land, owner_aadhaar ) "
					+ "VALUES " + "(?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, "
					+ "?, ?, to_date(cast(? as TEXT), 'YYYY-MM-DD'), ?, ?, " + "?, ?, ?, ?, ?,"
					+ " ?, ?, ?, to_date(cast(? as TEXT), 'YYYY-MM-DD'), ?," + " ?, ?, ?, ?, ?, ?, ?, ?)";
			
			List<CCRCCropData> cropDataList = getCcrcCropData(String.valueOf(villageCode));

			System.out.println("cropDataList--->"+cropDataList);
			
			
			for (CCRCCropData data : cropDataList) {
				try {
					String gender = mapGender(data.getTenantGender());

					int casteCode = getCasteCode(data.getCaste());
					String anubhavadarName = mapString(data.getWebland_Occupant_Name());
					String anubhavadarFname = mapString(data.getWebland_Occupant_Father_Name());

					double weblandOccupantExt = Double.parseDouble(data.getWebland_occupant_extent());
					double weblandTotalExt = Double.parseDouble(data.getWebland_total_extent());

					if (weblandOccupantExt > weblandTotalExt || weblandOccupantExt == 0 || weblandTotalExt == 0) {
						continue;
					}

					ccrcIns += entityManager.createNativeQuery(QRY_INS_CROP_DET_old)
							.setParameter(1,data.getCcrc_id())
							.setParameter(2, data.getDoc_Id())
							.setParameter(3, Integer.parseInt(data.getDistrict())).setParameter(4, data.getDName())
							.setParameter(5, Integer.parseInt(data.getMandal()))

							.setParameter(6, data.getMName()).setParameter(7, Integer.parseInt(data.getVillage()))
							.setParameter(8, data.getVName()).setParameter(9, data.getNameOf_TheTenantFarmer())
							.setParameter(10, Integer.parseInt(data.getKhata_no()))

							.setParameter(11, data.getSurvey_No())
							.setParameter(12, Double.parseDouble(data.getCard_Extent()))
							.setParameter(13, data.getEnroll_Date()).setParameter(14, casteCode)
							.setParameter(15, cropYear)

							.setParameter(16, season).setParameter(17, weblandTotalExt)
							.setParameter(18, weblandOccupantExt).setParameter(19, season + dcode + cropYear)
							.setParameter(20, sesdcode)

							.setParameter(21, sesmcode).setParameter(22, data.getTenantFather())
							.setParameter(23, data.getTenant_Aadhar()).setParameter(24, data.getEnroll_End_Date())
							.setParameter(25, data.getLand_nature())

							.setParameter(26, gender).setParameter(27, data.getNameOf_TheLandOwner())
							.setParameter(28, data.getLandOwnerFather()).setParameter(29, anubhavadarName)
							.setParameter(30, anubhavadarFname)
							.setParameter(31, Double.parseDouble(data.getWebland_Cultivatable_Land()))
							.setParameter(32, Double.parseDouble(data.getWebland_Uncultivated_Land()))
							.setParameter(33, data.getOwner_aadhaar())

							.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error inserting data: " + e.getMessage());
				}
			}
			if(ccrcIns>0) {
				String wholesrnoTempupd = "SELECT ecrop2024.update_wsno_regno_sjoint(:pattmast, :villageCode)";

				try {
					Query query = entityManager.createNativeQuery(wholesrnoTempupd);
					query.setParameter("pattmast", pattmast);
					query.setParameter("villageCode", villageCode);

					List<Object> result = query.getResultList();

					if (result != null && !result.isEmpty()) {
						Object singleResult = result.get(0);

						if (singleResult instanceof Number) {
						int	diffIns = ((Number) singleResult).intValue();

							System.out.println("diffIns-------->" + diffIns);

						} else {
							System.err.println("Unexpected result type: " + singleResult.getClass());
						}
					}
				} catch (Exception e) {
					// Log the error instead of printing the stack trace
					System.err.println("Error updating wsno: " + e.getMessage());
				}
				}
		}
	}
		return ccrcIns;
	}

	private String mapGender(String gender) {
		if (gender != null && !gender.isEmpty()) {
			if (gender.equalsIgnoreCase("Female")) {
				return "F";
			} else if (gender.equalsIgnoreCase("Male")) {
				return "M";
			} else if (gender.equalsIgnoreCase("Transgender")) {
				return "T";
			}
		}
		return "";
	}

	private int getCasteCode(String caste) {
		try {
			String queryCaste = "SELECT castecode FROM caste_mst WHERE caste = :caste";

			if (isSpecialCaste(caste)) {
				if (caste.trim().equalsIgnoreCase("OC")) {
					caste = "General";
					System.out.println("caste" + caste);
				} else if (caste.trim().equalsIgnoreCase("Minority")) {
					caste = "Minorities";
				}
			}

			Query query = entityManager.createNativeQuery(queryCaste);
			query.setParameter("caste", caste);

			List<Object> resultList = query.getResultList();

			if (resultList.isEmpty()) {
				System.err.println("No result found for caste: " + caste);
				return 0;
			}

			return Integer.parseInt(resultList.get(0).toString());
		} catch (Exception e) {
			System.err.println("Error while fetching caste code: " + e.getMessage());
			return 0;
		}
	}

	private boolean isSpecialCaste(String caste) {
		return caste.trim().equalsIgnoreCase("BC") || caste.trim().equalsIgnoreCase("SC")
				|| caste.trim().equalsIgnoreCase("ST") || caste.trim().equalsIgnoreCase("OC")
				|| caste.trim().equalsIgnoreCase("Minority") || caste.trim().equalsIgnoreCase("NA");
	}

	private String mapString(String value) {
		return (value == null) ? "NA" : value.toString();
	}

	@Transactional
	public List<CCRCCropData> getCcrcCropData( String villageCode) {
		try {

			String ccrcDetails = fetchCcrcDetailsFromURL( villageCode);

			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(ccrcDetails, new TypeReference<List<CCRCCropData>>() {
			});
		} catch (RuntimeException e) {
			handleConnectionTimeoutException(e);
			System.out.println("Connection timeout: Unable to connect to the server");
			e.printStackTrace();
			return new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public String fetchCcrcDetailsFromURL( String villageCode) {
		String status = "";
		//String url = "http://ccrc.ap.gov.in/ccrcrest/CCRCServiceVillageActive";	
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		 
         StringBuffer  response = null;
		try {		
			
           // String apiUrl = "http://103.129.75.175/CCRCAPI/Ecrop/GetCropDetails?Username=Administrator&Password=Administrator@789&ptype=V&villagecode=1914023&SurveyOrKhataNo=";
            String apiUrl = "http://103.129.75.175/CCRCAPI/Ecrop/GetCropDetails?Username=Administrator&Password=Administrator@789&ptype=V&villagecode="+villageCode+"&SurveyOrKhataNo=";
            // Create a URL object from the API URL string
            URL url = new URL(apiUrl);
            // Open a connection to the API URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Set the request method to POST
            conn.setRequestMethod("POST");
            // Set headers
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            // Create the POST data               
            // Write POST data to the connection
            OutputStream os = conn.getOutputStream();
          //  os.write(postData.getBytes());
            os.flush();
            os.close();
            int responseCode = conn.getResponseCode();
           // System.out.println("Response Code : " + responseCode);

            // Read the response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));          
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the JSON response
          //  System.out.println("API Response:");
          //  System.out.println(response.toString());

            // Disconnect the HttpURLConnection
            conn.disconnect();
			
			
		} catch (java.net.ConnectException e) {
			status = "Connection timed out: Unable to connect to the server";

			throw new RuntimeException("Connection timed out: Unable to connect to the server", e);
		} catch (Exception e) {
			status = "Error reading URL. Please Download Again";

			throw new RuntimeException("Error reading URL. Please Download Again", e);

		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				status = "Error closing URL reader";

				throw new RuntimeException("Error closing URL reader", e);
			}

			if (connection != null) {
				connection.disconnect();
			}
		}
		return response.toString();
	}

	private void handleConnectionTimeoutException(RuntimeException e) {

		System.out.println("Connection timeout: Unable to connect to the server");
		e.printStackTrace();
	}

	public static String decodeUnicodeEscapeSequences(String input) {
		return StringEscapeUtils.unescapeJava(input);
	}

	public List<CCRCLandDetails> getCcrcNewApiData(String wbvcode) {
		String ccrcNewApiResponse = getCcrcSurvedVillageData(wbvcode);
		ObjectMapper mapper = new ObjectMapper();
		List<CCRCLandDetails> cropList = new ArrayList<CCRCLandDetails>();
		try {

			JsonNode rootNode = mapper.readTree(ccrcNewApiResponse);
			JsonNode dataNode = rootNode.path("Data");

			if (dataNode.isArray()) {
				cropList = mapper.readValue(dataNode.toString(), new TypeReference<List<CCRCLandDetails>>() {
				});
			} else {

				System.err.println("Expected an array but got: " + dataNode.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cropList;
	}

	public static String getCcrcSurvedVillageData(String wbvcode) {
		try {
			String token = getKeyValue();
		
			URL url = new URL("http://103.129.75.175/Finalror/Ecrop/GCD");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);

			String inputData = "{\n" + "    \"Ptype\":\"V\",\n" + "    \"VillageCode\":\"" + wbvcode + "\",\n"
					+ "    \"Pvalue\":\"\"\n" + "}";

			try (java.io.OutputStream os = connection.getOutputStream()) {
				byte[] inputBytes = inputData.getBytes(java.nio.charset.StandardCharsets.UTF_8);
				os.write(inputBytes, 0, inputBytes.length);
			}

			int responseCode = connection.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + responseCode);
			}

			StringBuilder response = new StringBuilder();

			try (BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), java.nio.charset.StandardCharsets.UTF_8))) {
				String line;
				while ((line = br.readLine()) != null) {
					response.append(line);

				}

			}

			return response.toString();

		} catch (Exception e) {
			throw new RuntimeException("Error fetching Webland data", e);
		}

	}

	public static String getKeyValue() {
		try {
			URL url = new URL("http://103.129.75.175/Finalror/api/Token");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");

			String input = "{\n" + "    \"un\":\"Agriculture\",\n" + "    \"up\":\"Ag@2024$01\"\n" + "}";

			try (java.io.OutputStream os = connection.getOutputStream()) {
				byte[] inputBytes = input.getBytes(java.nio.charset.StandardCharsets.UTF_8);
				os.write(inputBytes, 0, inputBytes.length);
			}

			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + responseCode);
			}

			StringBuilder response = new StringBuilder();
			try (BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), java.nio.charset.StandardCharsets.UTF_8))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			}

			System.out.println("Token API Response: " + response.toString());

			JSONObject json = new JSONObject(response.toString());

			if (json.has("Data")) {
				return json.getString("Data");
			} else {
				throw new RuntimeException("Token not found in response: " + response.toString());
			}
		} catch (Exception e) {
			throw new RuntimeException("Error fetching API key", e);
		}
	}

}