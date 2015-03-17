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
public class InsectDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer insectID;
    private String groupName;
    private int sensitivityScore;
    private List<InsectImageDTO> insectImageList = new ArrayList<>();
    private List<EvaluationInsectDTO> evaluationInsectList = new ArrayList<>();

    public InsectDTO() {
    }

    public InsectDTO(Insect a) {
        this.insectID = a.getInsectID();
        this.groupName = a.getGroupName();
        this.sensitivityScore = a.getSensitivityScore();
    }

    public Integer getInsectID() {
        return insectID;
    }

    public void setInsectID(Integer insectID) {
        this.insectID = insectID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getSensitivityScore() {
        return sensitivityScore;
    }

    public void setSensitivityScore(int sensitivityScore) {
        this.sensitivityScore = sensitivityScore;
    }

    public List<InsectImageDTO> getInsectImageList() {
        return insectImageList;
    }

    public void setInsectImageList(List<InsectImageDTO> insectImageList) {
        this.insectImageList = insectImageList;
    }

    public List<EvaluationInsectDTO> getEvaluationInsectList() {
        return evaluationInsectList;
    }

    public void setEvaluationInsectList(List<EvaluationInsectDTO> evaluationInsectList) {
        this.evaluationInsectList = evaluationInsectList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insectID != null ? insectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsectDTO)) {
            return false;
        }
        InsectDTO other = (InsectDTO) object;
        if ((this.insectID == null && other.insectID != null) || (this.insectID != null && !this.insectID.equals(other.insectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.Insect[ insectID=" + insectID + " ]";
    }

}
