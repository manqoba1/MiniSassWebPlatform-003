/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.dto;

import com.boha.minisass.data.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class EvaluationSiteDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer evaluationSiteID;
    private Double latitude;
    private Double longitude;
    private Float accuracy;
    private long dateRegistered;
    private Integer categoryID;
    private Integer riverID;
    private String riverName, categoryName;
    private List<EvaluationDTO> evaluationList = new ArrayList<>();
    private RiverDTO river;
    private CategoryDTO category;

    public EvaluationSiteDTO() {
    }

    public EvaluationSiteDTO(Evaluationsite a) {
        this.evaluationSiteID = a.getEvaluationSiteID();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.dateRegistered = a.getDateRegistered().getTime();
        this.riverID = a.getRiver().getRiverID();
        this.riverName = a.getRiver().getRiverName();
        this.categoryID = a.getCategory().getCategoryId();
        this.categoryName = a.getCategory().getCategoryName();
        accuracy = a.getAccuracy();
        river = new RiverDTO(a.getRiver());
        category = new CategoryDTO(a.getCategory());
    }

    public RiverDTO getRiver() {
        return river;
    }

    public void setRiver(RiverDTO river) {
        this.river = river;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getRiverID() {
        return riverID;
    }

    public void setRiverID(Integer riverID) {
        this.riverID = riverID;
    }

    public String getRiverName() {
        return riverName;
    }

    public void setRiverName(String riverName) {
        this.riverName = riverName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getEvaluationSiteID() {
        return evaluationSiteID;
    }

    public void setEvaluationSiteID(Integer evaluationSiteID) {
        this.evaluationSiteID = evaluationSiteID;
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

    public long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public List<EvaluationDTO> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<EvaluationDTO> evaluationList) {
        this.evaluationList = evaluationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationSiteID != null ? evaluationSiteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluationSiteDTO)) {
            return false;
        }
        EvaluationSiteDTO other = (EvaluationSiteDTO) object;
        if ((this.evaluationSiteID == null && other.evaluationSiteID != null) || (this.evaluationSiteID != null && !this.evaluationSiteID.equals(other.evaluationSiteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.EvaluationSite[ evaluationSiteID=" + evaluationSiteID + " ]";
    }

}
