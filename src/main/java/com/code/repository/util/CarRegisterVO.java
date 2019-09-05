package com.code.repository.util;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CarRegisterVO {

    private Long id;
    private String carNo;
    private String customsCode;
    private String weight;
    private String feature;
    private String caseNumber;
    private String overseasCarNo;
    private String cabinetType;

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getOverseasCarNo() {
        return overseasCarNo;
    }

    public void setOverseasCarNo(String overseasCarNo) {
        this.overseasCarNo = overseasCarNo;
    }

    public String getCabinetType() {
        return cabinetType;
    }

    public void setCabinetType(String cabinetType) {
        this.cabinetType = cabinetType;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

}
