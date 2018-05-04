package com.nopi;

import java.io.IOException;
import java.sql.Timestamp;

public class Control {
    private Integer armId;
    private String description;

    private String motherBoardSerial;
    private String publicIp;
    private String pendriveSerial;
    private String MacAddress;
    private String location;
    private java.sql.Timestamp date;
    private String lastBootUpTime;
    private String controlType;

    public Control(String pendriveSerial) throws IOException {
        this.pendriveSerial = pendriveSerial;
        this.publicIp = NetworkUtility.getPublicIp();
        this.motherBoardSerial = PcInformationUtility.getPcModel();
        this.MacAddress = PcInformationUtility.getPCMacAddress();
        this.location = NetworkUtility.getPcLocation();
        this.date = new Timestamp(System.currentTimeMillis());
        this.lastBootUpTime = PcInformationUtility.getLastBootUpTime();
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

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Control{" +
                "armId=" + armId +
                ", description='" + description + '\'' +
                ", motherBoardSerial='" + motherBoardSerial + '\'' +
                ", publicIp='" + publicIp + '\'' +
                ", pendriveSerial='" + pendriveSerial + '\'' +
                ", MacAddress='" + MacAddress + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", lastBootUpTime='" + lastBootUpTime + '\'' +
                ", controlType='" + controlType + '\'' +
                '}';
    }
}
