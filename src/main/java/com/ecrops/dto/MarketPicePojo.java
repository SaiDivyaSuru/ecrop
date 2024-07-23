package com.ecrops.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MarketPicePojo {

    private Integer pid;
    private String state;
    private String mandi;
    private String commodity;
    private BigDecimal modalPrice;
    private BigDecimal tradedQuantity;
    private String quantityUom;
    private BigDecimal maxPrice;
    private BigDecimal soldQuantity;
    private BigDecimal minPrice;
    private BigDecimal arrivalQuantity;
    private Date tranDate;
    private Date dates;

 
    // Getters and Setters
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMandi() {
        return mandi;
    }

    public void setMandi(String mandi) {
        this.mandi = mandi;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public BigDecimal getModalPrice() {
        return modalPrice;
    }

    public void setModalPrice(BigDecimal modalPrice) {
        this.modalPrice = modalPrice;
    }

    public BigDecimal getTradedQuantity() {
        return tradedQuantity;
    }

    public void setTradedQuantity(BigDecimal tradedQuantity) {
        this.tradedQuantity = tradedQuantity;
    }

    public String getQuantityUom() {
        return quantityUom;
    }

    public void setQuantityUom(String quantityUom) {
        this.quantityUom = quantityUom;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(BigDecimal soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getArrivalQuantity() {
        return arrivalQuantity;
    }

    public void setArrivalQuantity(BigDecimal arrivalQuantity) {
        this.arrivalQuantity = arrivalQuantity;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}



   
}

