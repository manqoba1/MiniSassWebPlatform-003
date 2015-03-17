/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.dto;

import com.boha.minisass.data.Conditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CodeTribe1
 */
public class ConditionsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer conditionsID, categoryID;
    private String conditionName;
    private double low;
    private double high;
    private List<EvaluationDTO> evaluationList = new ArrayList<>();
    private CategoryDTO category;

    public ConditionsDTO() {

    }

    public ConditionsDTO(Conditions c) {
        this.conditionsID = c.getConditionsID();
        this.conditionName = c.getConditionName();
        this.high = c.getHigh();
        this.low = c.getLow();
        categoryID = c.getCategory().getCategoryId();
        category = new CategoryDTO(c.getCategory());
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getConditionsID() {
        return conditionsID;
    }

    public void setConditionsID(Integer conditionsID) {
        this.conditionsID = conditionsID;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public List<EvaluationDTO> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<EvaluationDTO> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

}
