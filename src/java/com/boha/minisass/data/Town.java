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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author CodeTribe1
 */
@Entity
@Table(name = "town")
@NamedQueries({
    @NamedQuery(name = "Town.findAll", query = "SELECT t FROM Town t"),
    @NamedQuery(name = "Town.findByTownID", query = "SELECT t FROM Town t WHERE t.townID = :townID"),
    @NamedQuery(name = "Town.findByProvinceID", query = "SELECT t FROM Town t WHERE t.province = :provinceID"),
    @NamedQuery(name = "Town.findByTownName", query = "SELECT t FROM Town t WHERE t.townName = :townName"),
    @NamedQuery(name = "Town.findByLatitude", query = "SELECT t FROM Town t WHERE t.latitude = :latitude"),
    @NamedQuery(name = "Town.findByLongitude", query = "SELECT t FROM Town t WHERE t.longitude = :longitude")})
public class Town implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "townID")
    private Integer townID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "townName")
    private String townName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @JoinColumn(name = "provinceID", referencedColumnName = "provinceID")
    @ManyToOne(optional = false)
    private Province province;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "town")
    private List<Team> teamList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "town")
    private List<Rivertown> rivertownList;

    public Town() {
    }

    public Town(Integer townID) {
        this.townID = townID;
    }

    public Town(Integer townID, String townName) {
        this.townID = townID;
        this.townName = townName;
    }

    public Integer getTownID() {
        return townID;
    }

    public void setTownID(Integer townID) {
        this.townID = townID;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

   

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public List<Rivertown> getRivertownList() {
        return rivertownList;
    }

    public void setRivertownList(List<Rivertown> rivertownList) {
        this.rivertownList = rivertownList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (townID != null ? townID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Town)) {
            return false;
        }
        Town other = (Town) object;
        if ((this.townID == null && other.townID != null) || (this.townID != null && !this.townID.equals(other.townID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.Town[ townID=" + townID + " ]";
    }
    
}
