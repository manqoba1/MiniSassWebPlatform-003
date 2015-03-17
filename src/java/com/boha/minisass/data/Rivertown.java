/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author CodeTribe1
 */
@Entity
@Table(name = "rivertown")
@NamedQueries({
    @NamedQuery(name = "Rivertown.findAll", query = "SELECT r FROM Rivertown r"),
    @NamedQuery(name = "Rivertown.findByRiverTownID", query = "SELECT r FROM Rivertown r WHERE r.riverTownID = :riverTownID")})
public class Rivertown implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "riverTownID")
    private Integer riverTownID;
    @JoinColumn(name = "riverID", referencedColumnName = "riverID")
    @ManyToOne(optional = false)
    private River river;
    @JoinColumn(name = "townID", referencedColumnName = "townID")
    @ManyToOne(optional = false)
    private Town town;

    public Rivertown() {
    }

    public Rivertown(Integer riverTownID) {
        this.riverTownID = riverTownID;
    }

    public Integer getRiverTownID() {
        return riverTownID;
    }

    public void setRiverTownID(Integer riverTownID) {
        this.riverTownID = riverTownID;
    }

    public River getRiver() {
        return river;
    }

    public void setRiver(River river) {
        this.river = river;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (riverTownID != null ? riverTownID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rivertown)) {
            return false;
        }
        Rivertown other = (Rivertown) object;
        if ((this.riverTownID == null && other.riverTownID != null) || (this.riverTownID != null && !this.riverTownID.equals(other.riverTownID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.Rivertown[ riverTownID=" + riverTownID + " ]";
    }

}
