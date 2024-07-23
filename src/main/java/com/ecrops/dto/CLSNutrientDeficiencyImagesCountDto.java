package com.ecrops.dto;

public class CLSNutrientDeficiencyImagesCountDto {
	
	private int totalDrcPendingNutrientDeficiencyImagesNo;
	
	private int totalDrcApprovedNutrientDeficiencyImagesNo;
	
	private int totalDrcRejectedNutrientDeficiencyImagesNo;
	
	public int getTotalDrcPendingNutrientDeficiencyImagesNo() {
		return totalDrcPendingNutrientDeficiencyImagesNo;
	}
	
	public void setTotalDrcPendingNutrientDeficiencyImagesNo(int totalDrcPendingNutrientDeficiencyImagesNo) {
		this.totalDrcPendingNutrientDeficiencyImagesNo = totalDrcPendingNutrientDeficiencyImagesNo;
	}
	
	public int getTotalDrcApprovedNutrientDeficiencyImagesNo() {
		return totalDrcApprovedNutrientDeficiencyImagesNo;
	}
	
	public void setTotalDrcApprovedNutrientDeficiencyImagesNo(int totalDrcApprovedNutrientDeficiencyImagesNo) {
		this.totalDrcApprovedNutrientDeficiencyImagesNo = totalDrcApprovedNutrientDeficiencyImagesNo;
	}
	
	public int getTotalDrcRejectedNutrientDeficiencyImagesNo() {
		return totalDrcRejectedNutrientDeficiencyImagesNo;
	}
	
	public void setTotalDrcRejectedNutrientDeficiencyImagesNo(int totalDrcRejectedNutrientDeficiencyImagesNo) {
		this.totalDrcRejectedNutrientDeficiencyImagesNo = totalDrcRejectedNutrientDeficiencyImagesNo;
	}

	public CLSNutrientDeficiencyImagesCountDto(int totalDrcPendingNutrientDeficiencyImagesNo,
			int totalDrcApprovedNutrientDeficiencyImagesNo, int totalDrcRejectedNutrientDeficiencyImagesNo) {
		super();
		this.totalDrcPendingNutrientDeficiencyImagesNo = totalDrcPendingNutrientDeficiencyImagesNo;
		this.totalDrcApprovedNutrientDeficiencyImagesNo = totalDrcApprovedNutrientDeficiencyImagesNo;
		this.totalDrcRejectedNutrientDeficiencyImagesNo = totalDrcRejectedNutrientDeficiencyImagesNo;
	}

	@Override
	public String toString() {
		return "CLSNutrientDeficiencyImagesCountDto [totalDrcPendingNutrientDeficiencyImagesNo="
				+ totalDrcPendingNutrientDeficiencyImagesNo + ", totalDrcApprovedNutrientDeficiencyImagesNo="
				+ totalDrcApprovedNutrientDeficiencyImagesNo + ", totalDrcRejectedNutrientDeficiencyImagesNo="
				+ totalDrcRejectedNutrientDeficiencyImagesNo + "]";
	}

	public CLSNutrientDeficiencyImagesCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
