/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.dto;

import com.boha.minisass.data.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class CountryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer countryID;
    private String countryName;
    private Double latitude;
    private Double longitude;
    private String countryCode;
    private List<ProvinceDTO> provinceList = new ArrayList<>();
    private List<RiverDTO> originRiverList= new ArrayList<>();
    private List<RiverDTO> endRiverList= new ArrayList<>();

    public CountryDTO() {
    }

    public CountryDTO(Integer countryID) {
        this.countryID = countryID;
    }

    public CountryDTO(Country a) {
        this.countryID = a.getCountryID();
        this.countryName = a.getCountryName();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.countryCode = a.getCountryCode();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<ProvinceDTO> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceDTO> provinceList) {
        this.provinceList = provinceList;
    }

    public List<RiverDTO> getOriginRiverList() {
        return originRiverList;
    }

    public void setOriginRiverList(List<RiverDTO> originRiverList) {
        this.originRiverList = originRiverList;
    }

    public List<RiverDTO> getEndRiverList() {
        return endRiverList;
    }

    public void setEndRiverList(List<RiverDTO> endRiverList) {
        this.endRiverList = endRiverList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryID != null ? countryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CountryDTO)) {
            return false;
        }
        CountryDTO other = (CountryDTO) object;
        if ((this.countryID == null && other.countryID != null) || (this.countryID != null && !this.countryID.equals(other.countryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.Country[ countryID=" + countryID + " ]";
    }

}
