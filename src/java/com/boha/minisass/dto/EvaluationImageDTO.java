/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.dto;

import com.boha.minisass.data.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aubreyM
 */
public class EvaluationImageDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer evaluationImageID;
    private long dateTaken;
    private String fileName;
    private Integer evaluationID;
    private Double longitude;
    private Double latitude;
    private Float accuracy;

    public EvaluationImageDTO() {
    }

    public EvaluationImageDTO(Evaluationimage a) {
        this.evaluationImageID = a.getEvaluationImageID();
        this.dateTaken = a.getDateTaken().getTime();
        this.fileName = a.getFileName();
        this.evaluationID = a.getEvaluation().getEvaluationID();
        longitude = a.getLongitude();
        latitude = a.getLatitude();
        accuracy = a.getAccuracy();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public Integer getEvaluationImageID() {
        return evaluationImageID;
    }

    public void setEvaluationImageID(Integer evaluationImageID) {
        this.evaluationImageID = evaluationImageID;
    }

    public long getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(long dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationImageID != null ? evaluationImageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluationImageDTO)) {
            return false;
        }
        EvaluationImageDTO other = (EvaluationImageDTO) object;
        if ((this.evaluationImageID == null && other.evaluationImageID != null) || (this.evaluationImageID != null && !this.evaluationImageID.equals(other.evaluationImageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.EvaluationImage[ evaluationImageID=" + evaluationImageID + " ]";
    }

}
