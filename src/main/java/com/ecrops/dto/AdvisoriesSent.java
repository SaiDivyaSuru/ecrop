package com.ecrops.dto;

public class AdvisoriesSent {

	
	
	private String crop;
	
	private String dateSent;
	
	private String message;
	
	private String district;
	
	private String totalSmsSent;

	public String getTotalSmsSent() {
		return totalSmsSent;
	}

	public void setTotalSmsSent(String totalSmsSent) {
		this.totalSmsSent = totalSmsSent;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public String getDateSent() {
		return dateSent;
	}

	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	
}
