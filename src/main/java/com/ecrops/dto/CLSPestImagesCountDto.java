package com.ecrops.dto;

public class CLSPestImagesCountDto {
	
	private int totalDrcPendingPestImagesNo;
	
	private int totalDrcApprovedPestImagesNo;
	
	private int totalDrcRejectedPestImagesNo;

	public int getTotalDrcPendingPestImagesNo() {
		return totalDrcPendingPestImagesNo;
	}

	public void setTotalDrcPendingPestImagesNo(int totalDrcPendingPestImagesNo) {
		this.totalDrcPendingPestImagesNo = totalDrcPendingPestImagesNo;
	}

	public int getTotalDrcApprovedPestImagesNo() {
		return totalDrcApprovedPestImagesNo;
	}

	public void setTotalDrcApprovedPestImagesNo(int totalDrcApprovedPestImagesNo) {
		this.totalDrcApprovedPestImagesNo = totalDrcApprovedPestImagesNo;
	}

	public int getTotalDrcRejectedPestImagesNo() {
		return totalDrcRejectedPestImagesNo;
	}

	public void setTotalDrcRejectedPestImagesNo(int totalDrcRejectedPestImagesNo) {
		this.totalDrcRejectedPestImagesNo = totalDrcRejectedPestImagesNo;
	}

	public CLSPestImagesCountDto(int totalDrcPendingPestImagesNo, int totalDrcApprovedPestImagesNo,
			int totalDrcRejectedPestImagesNo) {
		super();
		this.totalDrcPendingPestImagesNo = totalDrcPendingPestImagesNo;
		this.totalDrcApprovedPestImagesNo = totalDrcApprovedPestImagesNo;
		this.totalDrcRejectedPestImagesNo = totalDrcRejectedPestImagesNo;
	}

	public CLSPestImagesCountDto() {
		super();
	}
	
	

}
