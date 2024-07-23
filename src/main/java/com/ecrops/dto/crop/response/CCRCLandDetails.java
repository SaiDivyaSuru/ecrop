package com.ecrops.dto.crop.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CCRCLandDetails {

	@JsonProperty("CCRCID")
	private String ccrcId;

	@JsonProperty("DistrictCode")
	private String districtCode;

	@JsonProperty("DistrictName")
	private String districtName;

	@JsonProperty("MandalCode")
	private String mandalCode;

	@JsonProperty("MandalName")
	private String mandalName;

	@JsonProperty("VillageCode")
	private String villageCode;

	@JsonProperty("VillageName")
	private String villageName;

	@JsonProperty("LandOwnerAadhar")
	private String landOwnerAadhar;

	@JsonProperty("LandOwnerName")
	private String landOwnerName;

	@JsonProperty("LandOwnerFatherName")
	private String landOwnerFatherName;

	@JsonProperty("TenantAadharNo")
	private String tenantAadharNo;

	@JsonProperty("TenantFarmerName")
	private String tenantFarmerName;

	@JsonProperty("TenantFarmerFatherName")
	private String tenantFarmerFatherName;

	@JsonProperty("OccupantName")
	private String occupantName;

	@JsonProperty("OccupantFatherName")
	private String occupantFatherName;

	@JsonProperty("TenantCaste")
	private String tenantCaste;

	@JsonProperty("TenantGender")
	private String tenantGender;

	@JsonProperty("KhataNo")
	private String khataNo;

	@JsonProperty("LPM_No")
	private String lpmNo;

	@JsonProperty("Extent")
	private String extent;

	@JsonProperty("LandNature")
	private String landNature;

	@JsonProperty("Card_Extent")
	private String cardExtent;

	@JsonProperty("EnrollDate")
	private String enrollDate;

	@JsonProperty("EnrollEndDate")
	private String enrollEndDate;

	@JsonProperty("TenantAddress")
	private String tenantAddress;

	@JsonProperty("TenantMobile")
	private String tenantMobile;

	@JsonProperty("ULPIN")
	private String ulpin;

	public CCRCLandDetails() {

	}

	public CCRCLandDetails(String ccrcId, String districtCode, String districtName, String mandalCode,
			String mandalName, String villageCode, String villageName, String landOwnerAadhar, String landOwnerName,
			String landOwnerFatherName, String tenantAadharNo, String tenantFarmerName, String tenantFarmerFatherName,
			String occupantName, String occupantFatherName, String tenantCaste, String tenantGender, String khataNo,
			String lpmNo, String extent, String landNature, String cardExtent, String enrollDate, String enrollEndDate,
			String tenantAddress, String tenantMobile, String ulpin) {
		super();
		this.ccrcId = ccrcId;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.mandalCode = mandalCode;
		this.mandalName = mandalName;
		this.villageCode = villageCode;
		this.villageName = villageName;
		this.landOwnerAadhar = landOwnerAadhar;
		this.landOwnerName = landOwnerName;
		this.landOwnerFatherName = landOwnerFatherName;
		this.tenantAadharNo = tenantAadharNo;
		this.tenantFarmerName = tenantFarmerName;
		this.tenantFarmerFatherName = tenantFarmerFatherName;
		this.occupantName = occupantName;
		this.occupantFatherName = occupantFatherName;
		this.tenantCaste = tenantCaste;
		this.tenantGender = tenantGender;
		this.khataNo = khataNo;
		this.lpmNo = lpmNo;
		this.extent = extent;
		this.landNature = landNature;
		this.cardExtent = cardExtent;
		this.enrollDate = enrollDate;
		this.enrollEndDate = enrollEndDate;
		this.tenantAddress = tenantAddress;
		this.tenantMobile = tenantMobile;
		this.ulpin = ulpin;
	}

	public String getCcrcId() {
		return ccrcId;
	}

	public void setCcrcId(String ccrcId) {
		this.ccrcId = ccrcId;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getMandalCode() {
		return mandalCode;
	}

	public void setMandalCode(String mandalCode) {
		this.mandalCode = mandalCode;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getLandOwnerAadhar() {
		return landOwnerAadhar;
	}

	public void setLandOwnerAadhar(String landOwnerAadhar) {
		this.landOwnerAadhar = landOwnerAadhar;
	}

	public String getLandOwnerName() {
		return landOwnerName;
	}

	public void setLandOwnerName(String landOwnerName) {
		this.landOwnerName = landOwnerName;
	}

	public String getLandOwnerFatherName() {
		return landOwnerFatherName;
	}

	public void setLandOwnerFatherName(String landOwnerFatherName) {
		this.landOwnerFatherName = landOwnerFatherName;
	}

	public String getTenantAadharNo() {
		return tenantAadharNo;
	}

	public void setTenantAadharNo(String tenantAadharNo) {
		this.tenantAadharNo = tenantAadharNo;
	}

	public String getTenantFarmerName() {
		return tenantFarmerName;
	}

	public void setTenantFarmerName(String tenantFarmerName) {
		this.tenantFarmerName = tenantFarmerName;
	}

	public String getTenantFarmerFatherName() {
		return tenantFarmerFatherName;
	}

	public void setTenantFarmerFatherName(String tenantFarmerFatherName) {
		this.tenantFarmerFatherName = tenantFarmerFatherName;
	}

	public String getOccupantName() {
		return occupantName;
	}

	public void setOccupantName(String occupantName) {
		this.occupantName = occupantName;
	}

	public String getOccupantFatherName() {
		return occupantFatherName;
	}

	public void setOccupantFatherName(String occupantFatherName) {
		this.occupantFatherName = occupantFatherName;
	}

	public String getTenantCaste() {
		return tenantCaste;
	}

	public void setTenantCaste(String tenantCaste) {
		this.tenantCaste = tenantCaste;
	}

	public String getTenantGender() {
		return tenantGender;
	}

	public void setTenantGender(String tenantGender) {
		this.tenantGender = tenantGender;
	}

	public String getKhataNo() {
		return khataNo;
	}

	public void setKhataNo(String khataNo) {
		this.khataNo = khataNo;
	}

	public String getLpmNo() {
		return lpmNo;
	}

	public void setLpmNo(String lpmNo) {
		this.lpmNo = lpmNo;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	public String getLandNature() {
		return landNature;
	}

	public void setLandNature(String landNature) {
		this.landNature = landNature;
	}

	public String getCardExtent() {
		return cardExtent;
	}

	public void setCardExtent(String cardExtent) {
		this.cardExtent = cardExtent;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getEnrollEndDate() {
		return enrollEndDate;
	}

	public void setEnrollEndDate(String enrollEndDate) {
		this.enrollEndDate = enrollEndDate;
	}

	public String getTenantAddress() {
		return tenantAddress;
	}

	public void setTenantAddress(String tenantAddress) {
		this.tenantAddress = tenantAddress;
	}

	public String getTenantMobile() {
		return tenantMobile;
	}

	public void setTenantMobile(String tenantMobile) {
		this.tenantMobile = tenantMobile;
	}

	public String getUlpin() {
		return ulpin;
	}

	public void setUlpin(String ulpin) {
		this.ulpin = ulpin;
	}

	@Override
	public String toString() {
		return "CCRCLandDetails [ccrcId=" + ccrcId + ", districtCode=" + districtCode + ", districtName=" + districtName
				+ ", mandalCode=" + mandalCode + ", mandalName=" + mandalName + ", villageCode=" + villageCode
				+ ", villageName=" + villageName + ", landOwnerAadhar=" + landOwnerAadhar + ", landOwnerName="
				+ landOwnerName + ", landOwnerFatherName=" + landOwnerFatherName + ", tenantAadharNo=" + tenantAadharNo
				+ ", tenantFarmerName=" + tenantFarmerName + ", tenantFarmerFatherName=" + tenantFarmerFatherName
				+ ", occupantName=" + occupantName + ", occupantFatherName=" + occupantFatherName + ", tenantCaste="
				+ tenantCaste + ", tenantGender=" + tenantGender + ", khataNo=" + khataNo + ", lpmNo=" + lpmNo
				+ ", extent=" + extent + ", landNature=" + landNature + ", cardExtent=" + cardExtent + ", enrollDate="
				+ enrollDate + ", enrollEndDate=" + enrollEndDate + ", tenantAddress=" + tenantAddress
				+ ", tenantMobile=" + tenantMobile + ", ulpin=" + ulpin + "]";
	}

}
