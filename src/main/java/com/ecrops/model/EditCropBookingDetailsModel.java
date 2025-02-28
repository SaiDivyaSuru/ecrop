package com.ecrops.model;

import org.springframework.stereotype.Component;

@Component
public class EditCropBookingDetailsModel {

    private Integer cropYear;

    private Integer vCode;

    private Integer correctionType;

    private String searchType;

    private String aadharNo;

    private String kathaNo;

    private String surveyNo;

    private String season;
    
  //  private Integer rec_id;

    public Integer getCropYear() {
        return cropYear;
    }

    public void setCropYear(Integer cropYear) {
        this.cropYear = cropYear;
    }

    public Integer getvCode() {
        return vCode;
    }

    public void setvCode(Integer vCode) {
        this.vCode = vCode;
    }

    public Integer getCorrectionType() {
        return correctionType;
    }

    public void setCorrectionType(Integer correctionType) {
        this.correctionType = correctionType;
    }

    public String getSearchType() {
        return searchType != null ? searchType : "";
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getKathaNo() {
        return kathaNo;
    }

    public void setKathaNo(String kathaNo) {
        this.kathaNo = kathaNo;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

//	public Integer getRec_id() {
//		return rec_id;
//	}
//
//	public void setRec_id(Integer rec_id) {
//		this.rec_id = rec_id;
//	}
    
    
}
