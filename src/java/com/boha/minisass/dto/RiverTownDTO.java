/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.dto;

import com.boha.minisass.data.*;
import java.io.Serializable;

/**
 *
 * @author aubreyM
 */
public class RiverTownDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer riverTownID, riverID, townID;
    private RiverDTO river;
    private TownDTO town;

    public RiverTownDTO() {
    }

    public RiverTownDTO(Rivertown a) {
        
        riverTownID = a.getRiverTownID();
        riverID = a.getRiver().getRiverID();
        townID = a.getTown().getTownID();
        river = new RiverDTO(a.getRiver());
        town = new TownDTO(a.getTown());
    }

    public TownDTO getTown() {
        return town;
    }

    public void setTown(TownDTO town) {
        this.town = town;
    }

    public RiverDTO getRiver() {
        return river;
    }

    public void setRiver(RiverDTO river) {
        this.river = river;
    }

    public Integer getRiverID() {
        return riverID;
    }

    public void setRiverID(Integer riverID) {
        this.riverID = riverID;
    }

    public Integer getTownID() {
        return townID;
    }

    public void setTownID(Integer townID) {
        this.townID = townID;
    }

    public Integer getRiverTownID() {
        return riverTownID;
    }

    public void setRiverTownID(Integer riverTownID) {
        this.riverTownID = riverTownID;
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
        if (!(object instanceof RiverTownDTO)) {
            return false;
        }
        RiverTownDTO other = (RiverTownDTO) object;
        if ((this.riverTownID == null && other.riverTownID != null) || (this.riverTownID != null && !this.riverTownID.equals(other.riverTownID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.RiverTown[ riverTownID=" + riverTownID + " ]";
    }

}
