package com.ecrops.dto;

public class CLSLifespanImagesCountDto {
	
	private int totalDrcPendingLifespanImagesNo;
	
	private int totalDrcApprovedLifespanImagesNo;
	
	private int totalDrcRejectedLifespanImagesNo;

	public int getTotalDrcPendingLifespanImagesNo() {
		return totalDrcPendingLifespanImagesNo;
	}

	public void setTotalDrcPendingLifespanImagesNo(int totalDrcPendingLifespanImagesNo) {
		this.totalDrcPendingLifespanImagesNo = totalDrcPendingLifespanImagesNo;
	}

	public int getTotalDrcApprovedLifespanImagesNo() {
		return totalDrcApprovedLifespanImagesNo;
	}

	public void setTotalDrcApprovedLifespanImagesNo(int totalDrcApprovedLifespanImagesNo) {
		this.totalDrcApprovedLifespanImagesNo = totalDrcApprovedLifespanImagesNo;
	}

	public int getTotalDrcRejectedLifespanImagesNo() {
		return totalDrcRejectedLifespanImagesNo;
	}

	public void setTotalDrcRejectedLifespanImagesNo(int totalDrcRejectedLifespanImagesNo) {
		this.totalDrcRejectedLifespanImagesNo = totalDrcRejectedLifespanImagesNo;
	}

	public CLSLifespanImagesCountDto(int totalDrcPendingLifespanImagesNo, int totalDrcApprovedLifespanImagesNo,
			int totalDrcRejectedLifespanImagesNo) {
		super();
		this.totalDrcPendingLifespanImagesNo = totalDrcPendingLifespanImagesNo;
		this.totalDrcApprovedLifespanImagesNo = totalDrcApprovedLifespanImagesNo;
		this.totalDrcRejectedLifespanImagesNo = totalDrcRejectedLifespanImagesNo;
	}
	

	public CLSLifespanImagesCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CLSLifespanImagesCountDto [totalDrcPendingLifespanImagesNo=" + totalDrcPendingLifespanImagesNo
				+ ", totalDrcApprovedLifespanImagesNo=" + totalDrcApprovedLifespanImagesNo
				+ ", totalDrcRejectedLifespanImagesNo=" + totalDrcRejectedLifespanImagesNo + "]";
	}

	
	

}
