package com.ecrops.entity;

public class CLSCropWisePojo {

	
	private Integer dcode;
	
	private String dname;
	
	private Integer mcode;
	
	private String mname;
	
	private Integer vcode;
	
	private String vname;
	
	private String cropnature;

	private String cr_vcode;

	private String drc_appr_status;

	private String photosuploaded;

	private int totalPhotosUploaded;
	private int totalDrcApprStatus;
	private int totalPsApprStatus;
	private int totalrbks;
	

	public Integer getDcode() {
		return dcode;
	}

	public void setDcode(Integer dcode) {
		this.dcode = dcode;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getMcode() {
		return mcode;
	}

	public void setMcode(Integer mcode) {
		this.mcode = mcode;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Integer getVcode() {
		return vcode;
	}

	public void setVcode(Integer vcode) {
		this.vcode = vcode;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public int getTotalrbks() {
		return totalrbks;
	}

	public void setTotalrbks(int totalrbks) {
		this.totalrbks = totalrbks;
	}

	public int getTotalPhotosUploaded() {
		return totalPhotosUploaded;
	}

	public void setTotalPhotosUploaded(int totalPhotosUploaded) {
		this.totalPhotosUploaded = totalPhotosUploaded;
	}

	public int getTotalDrcApprStatus() {
		return totalDrcApprStatus;
	}

	public void setTotalDrcApprStatus(int totalDrcApprStatus) {
		this.totalDrcApprStatus = totalDrcApprStatus;
	}

	public int getTotalPsApprStatus() {
		return totalPsApprStatus;
	}

	public void setTotalPsApprStatus(int totalPsApprStatus) {
		this.totalPsApprStatus = totalPsApprStatus;
	}

	public String getPhotosuploaded() {
		return photosuploaded;
	}

	public void setPhotosuploaded(String photosuploaded) {
		this.photosuploaded = photosuploaded;
	}

	private String ps_appr_status;

	public String getPs_appr_status() {
		return ps_appr_status;
	}

	public void setPs_appr_status(String ps_appr_status) {
		this.ps_appr_status = ps_appr_status;
	}

	public String getCropnature() {
		return cropnature;
	}

	public void setCropnature(String cropnature) {
		this.cropnature = cropnature;
	}

	public String getCr_vcode() {
		return cr_vcode;
	}

	public void setCr_vcode(String cr_vcode) {
		this.cr_vcode = cr_vcode;
	}

	public String getDrc_appr_status() {
		return drc_appr_status;
	}

	public void setDrc_appr_status(String drc_appr_status) {
		this.drc_appr_status = drc_appr_status;
	}

}
