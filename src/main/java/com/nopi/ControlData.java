package com.nopi;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class ControlData {
    private Integer armId;
    private String description;

    private String motherBoardSerial;
    private String publicIp;
    private String pendriveSerial;
    private String location;
    private java.sql.Timestamp date;
    private String lastBootUpTime;
    private String controlType;
    private BigDecimal income;
    private BigDecimal outcome;
    private BigDecimal chitanta;
    private String errorDescription;
    private String newInstall;

    private ControlFinancialData controlFinancialData;

    public ControlFinancialData getControlFinancialData() {
        return controlFinancialData;
    }

    public ControlData setControlFinancialData(ControlFinancialData controlFinancialData) {
        this.controlFinancialData = controlFinancialData;
        return this;
    }

    public ControlData(String pendriveSerial) throws IOException, WmicException {
        this.pendriveSerial = pendriveSerial;
        this.publicIp = NetworkUtility.getPublicIp();
        this.motherBoardSerial = PcInformationUtility.getPcModel();
        this.location = NetworkUtility.getPcLocation();
        this.lastBootUpTime = PcInformationUtility.getLastBootUpTime();
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public ControlData setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
        return this;
    }

    public String getNewInstall() {
        return newInstall;
    }

    public ControlData setNewInstall(String newInstall) {
        this.newInstall = newInstall;
        return this;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getOutcome() {
        return outcome;
    }

    public void setOutcome(BigDecimal outcome) {
        this.outcome = outcome;
    }

    public BigDecimal getChitanta() {
        return chitanta;
    }

    public void setChitanta(BigDecimal chitanta) {
        this.chitanta = chitanta;
    }

    public String getLastBootUpTime() {
        return lastBootUpTime;
    }

    public void setLastBootUpTime(String lastBootUpTime) {
        this.lastBootUpTime = lastBootUpTime;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getArmId() {
        return armId;
    }

    public void setArmId(Integer armId) {
        this.armId = armId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMotherBoardSerial() {
        return motherBoardSerial;
    }

    public void setMotherBoardSerial(String motherBoardSerial) {
        this.motherBoardSerial = motherBoardSerial;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getPendriveSerial() {
        return pendriveSerial;
    }

    public void setPendriveSerial(String pendriveSerial) {
        this.pendriveSerial = pendriveSerial;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ControlData{" +
                "armId=" + armId +
                ", description='" + description + '\'' +
                ", motherBoardSerial='" + motherBoardSerial + '\'' +
                ", publicIp='" + publicIp + '\'' +
                ", pendriveSerial='" + pendriveSerial + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", lastBootUpTime='" + lastBootUpTime + '\'' +
                ", controlType='" + controlType + '\'' +
                ", income=" + income +
                ", outcome=" + outcome +
                ", chitanta=" + chitanta +
                '}';
    }
}
