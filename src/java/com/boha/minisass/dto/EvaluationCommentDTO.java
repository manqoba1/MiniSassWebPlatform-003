/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.dto;

import com.boha.minisass.data.Evaluationcomment;
import java.io.Serializable;

/**
 *
 * @author aubreyM
 */
public class EvaluationCommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer evaluationCommentID;
    private Integer evaluationID, commentID;
    private CommentDTO comment;
    private EvaluationDTO evaluation;

    public EvaluationCommentDTO() {
    }

    public EvaluationCommentDTO(Evaluationcomment a) {
        evaluationCommentID = a.getEvaluationCommentID();
        evaluationID = a.getEvaluation().getEvaluationID();
        comment = new CommentDTO(a.getComment());
        evaluation = new EvaluationDTO(a.getEvaluation());
        commentID = a.getComment().getCommentID();
    }

    public EvaluationDTO getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationDTO evaluation) {
        this.evaluation = evaluation;
    }

    public Integer getEvaluationCommentID() {
        return evaluationCommentID;
    }

    public void setEvaluationCommentID(Integer evaluationCommentID) {
        this.evaluationCommentID = evaluationCommentID;
    }

    public Integer getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public CommentDTO getComment() {
        return comment;
    }

    public void setComment(CommentDTO comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationCommentID != null ? evaluationCommentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluationCommentDTO)) {
            return false;
        }
        EvaluationCommentDTO other = (EvaluationCommentDTO) object;
        if ((this.evaluationCommentID == null && other.evaluationCommentID != null) || (this.evaluationCommentID != null && !this.evaluationCommentID.equals(other.evaluationCommentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.EvaluationComment[ evaluationCommentID=" + evaluationCommentID + " ]";
    }

}
