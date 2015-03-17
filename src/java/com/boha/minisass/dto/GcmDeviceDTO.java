/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.dto;

import com.boha.minisass.data.Gcmdevice;

/**
 *
 * @author aubreyM
 */
public class GcmDeviceDTO {

    private Integer gcmDeviceID;
    private String registrationID;
    private String manufacturer;
    private String model;
    private String product;
    private Integer messageCount;
    private long dateRegistered;
    private String serialNumber, androidVersion;
    private Integer teamMemberID, teamID;
    private TeamMemberDTO teamMember;
    private TeamDTO team;

    public GcmDeviceDTO() {
    }

    public GcmDeviceDTO(Gcmdevice a) {
        gcmDeviceID = a.getGcmDeviceID();
        registrationID = a.getRegistrationID();
        manufacturer = a.getManufacturer();
        model = a.getModel();
        product = a.getProduct();
        messageCount = a.getMessageCount();
        dateRegistered = a.getDateRegistered().getTime();
        serialNumber = a.getSerialNumber();
        androidVersion = a.getAndroidVersion();
        teamID = a.getTeam().getTeamID();
        teamMemberID = a.getTeamMember().getTeamMemberID();

    }

    public TeamMemberDTO getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMemberDTO teamMember) {
        this.teamMember = teamMember;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public Integer getGcmDeviceID() {
        return gcmDeviceID;
    }

    public void setGcmDeviceID(Integer gcmDeviceID) {
        this.gcmDeviceID = gcmDeviceID;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    public Integer getTeamMemberID() {
        return teamMemberID;
    }

    public void setTeamMemberID(Integer teamMemberID) {
        this.teamMemberID = teamMemberID;
    }

}
