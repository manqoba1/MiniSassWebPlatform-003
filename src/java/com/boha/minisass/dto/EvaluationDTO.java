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
public class EvaluationDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer evaluationID, teamMemberID, conditionsID, evaluationSiteID;
    private long evaluationDate;
    private String comment, conditionName, teamName;
    private Double score;
    private Double pH;
    private String remarks;
    private Double waterTemperature;
    private Double oxygen;
    private Double waterClarity;
    private List<EvaluationImageDTO> evaluationImageList = new ArrayList<>();
    private TeamMemberDTO teamMember;
    private EvaluationSiteDTO evaluationSite;
    private ConditionsDTO conditions;
    private List<EvaluationInsectDTO> evaluationInsectList = new ArrayList<>();
    private List<EvaluationCommentDTO> evaluationCommentList = new ArrayList<>();

    public EvaluationDTO() {
    }

    public EvaluationDTO(Evaluation a) {
        this.evaluationID = a.getEvaluationID();
        this.evaluationDate = a.getEvaluationDate().getTime();
        this.score = a.getScore();
        this.pH = a.getPH();
        this.remarks = a.getRemarks();
        this.waterClarity = a.getWaterClarity();
        this.oxygen = a.getOxygen();
        conditionName = a.getConditions().getConditionName();
        this.waterTemperature = a.getWaterTemperature();
        conditionsID = a.getConditions().getConditionsID();
        teamMemberID = a.getTeamMember().getTeamMemberID();
        teamMember = new TeamMemberDTO(a.getTeamMember());
        conditions = new ConditionsDTO(a.getConditions());
        evaluationSiteID = a.getEvaluationSite().getEvaluationSiteID();
        evaluationSite = new EvaluationSiteDTO(a.getEvaluationSite());
        teamName = a.getTeamMember().getTeam().getTeamName();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<EvaluationCommentDTO> getEvaluationCommentList() {
        return evaluationCommentList;
    }

    public void setEvaluationCommentList(List<EvaluationCommentDTO> evaluationCommentList) {
        this.evaluationCommentList = evaluationCommentList;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getEvaluationID() {
        return evaluationID;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public void setEvaluationID(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public Integer getTeamMemberID() {
        return teamMemberID;
    }

    public void setTeamMemberID(Integer teamMemberID) {
        this.teamMemberID = teamMemberID;
    }

    public Integer getEvaluationSiteID() {
        return evaluationSiteID;
    }

    public void setEvaluationSiteID(Integer evaluationSiteID) {
        this.evaluationSiteID = evaluationSiteID;
    }

    public long getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(long evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getPH() {
        return pH;
    }

    public void setPH(Double pH) {
        this.pH = pH;
    }

    public Double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(Double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public Double getOxygen() {
        return oxygen;
    }

    public void setOxygen(Double oxygen) {
        this.oxygen = oxygen;
    }

    public Double getWaterClarity() {
        return waterClarity;
    }

    public void setWaterClarity(Double waterClarity) {
        this.waterClarity = waterClarity;
    }

    public List<EvaluationImageDTO> getEvaluationImageList() {
        return evaluationImageList;
    }

    public void setEvaluationImageList(List<EvaluationImageDTO> evaluationImageList) {
        this.evaluationImageList = evaluationImageList;
    }

    public Double getpH() {
        return pH;
    }

    public void setpH(Double pH) {
        this.pH = pH;
    }

    public TeamMemberDTO getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMemberDTO teamMember) {
        this.teamMember = teamMember;
    }

    public EvaluationSiteDTO getEvaluationSite() {
        return evaluationSite;
    }

    public void setEvaluationSite(EvaluationSiteDTO evaluationSite) {
        this.evaluationSite = evaluationSite;
    }

    public List<EvaluationInsectDTO> getEvaluationInsectList() {
        return evaluationInsectList;
    }

    public void setEvaluationInsectList(List<EvaluationInsectDTO> evaluationInsectList) {
        this.evaluationInsectList = evaluationInsectList;
    }

    public Integer getConditionsID() {
        return conditionsID;
    }

    public void setConditionsID(Integer conditionsID) {
        this.conditionsID = conditionsID;
    }

    public ConditionsDTO getConditions() {
        return conditions;
    }

    public void setConditions(ConditionsDTO conditions) {
        this.conditions = conditions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationID != null ? evaluationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluationDTO)) {
            return false;
        }
        EvaluationDTO other = (EvaluationDTO) object;
        if ((this.evaluationID == null && other.evaluationID != null) || (this.evaluationID != null && !this.evaluationID.equals(other.evaluationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.Evaluation[ evaluationID=" + evaluationID + " ]";
    }

}
