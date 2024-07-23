package com.ecrops.dto;

public class CLSDiseaseImagesCountDto {

	private int gettotalDrcpendingDiseaseImagesNo;
	
	private int gettotalDrcaprrovedDiseaseImagesNo;
	
	private int gettotalDrcRejectedDiseaseImagesNo;

	public int getGettotalDrcpendingDiseaseImagesNo() {
		return gettotalDrcpendingDiseaseImagesNo;
	}

	public void setGettotalDrcpendingDiseaseImagesNo(int gettotalDrcpendingDiseaseImagesNo) {
		this.gettotalDrcpendingDiseaseImagesNo = gettotalDrcpendingDiseaseImagesNo;
	}

	public int getGettotalDrcaprrovedDiseaseImagesNo() {
		return gettotalDrcaprrovedDiseaseImagesNo;
	}

	public void setGettotalDrcaprrovedDiseaseImagesNo(int gettotalDrcaprrovedDiseaseImagesNo) {
		this.gettotalDrcaprrovedDiseaseImagesNo = gettotalDrcaprrovedDiseaseImagesNo;
	}

	public int getGettotalDrcRejectedDiseaseImagesNo() {
		return gettotalDrcRejectedDiseaseImagesNo;
	}

	public void setGettotalDrcRejectedDiseaseImagesNo(int gettotalDrcRejectedDiseaseImagesNo) {
		this.gettotalDrcRejectedDiseaseImagesNo = gettotalDrcRejectedDiseaseImagesNo;
	}

	@Override
	public String toString() {
		return "CLSDiseaseImagesCountDto [gettotalDrcpendingDiseaseImagesNo=" + gettotalDrcpendingDiseaseImagesNo
				+ ", gettotalDrcaprrovedDiseaseImagesNo=" + gettotalDrcaprrovedDiseaseImagesNo
				+ ", gettotalDrcRejectedDiseaseImagesNo=" + gettotalDrcRejectedDiseaseImagesNo + "]";
	}

	public CLSDiseaseImagesCountDto(int gettotalDrcpendingDiseaseImagesNo, int gettotalDrcaprrovedDiseaseImagesNo,
			int gettotalDrcRejectedDiseaseImagesNo) {
		super();
		this.gettotalDrcpendingDiseaseImagesNo = gettotalDrcpendingDiseaseImagesNo;
		this.gettotalDrcaprrovedDiseaseImagesNo = gettotalDrcaprrovedDiseaseImagesNo;
		this.gettotalDrcRejectedDiseaseImagesNo = gettotalDrcRejectedDiseaseImagesNo;
	}

	public CLSDiseaseImagesCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
