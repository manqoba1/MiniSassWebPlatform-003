/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author CodeTribe1
 */
@Entity
@Table(name = "errorstoreandroid")
@NamedQueries({
    @NamedQuery(name = "Errorstoreandroid.findAll", query = "SELECT e FROM Errorstoreandroid e"),
    @NamedQuery(name = "Errorstoreandroid.findByErrorStoreAndroidID", query = "SELECT e FROM Errorstoreandroid e WHERE e.errorStoreAndroidID = :errorStoreAndroidID"),
    @NamedQuery(name = "Errorstoreandroid.findByErrorDate", query = "SELECT e FROM Errorstoreandroid e WHERE e.errorDate = :errorDate"),
    @NamedQuery(name = "Errorstoreandroid.findByPackageName", query = "SELECT e FROM Errorstoreandroid e WHERE e.packageName = :packageName"),
    @NamedQuery(name = "Errorstoreandroid.findByAppVersionName", query = "SELECT e FROM Errorstoreandroid e WHERE e.appVersionName = :appVersionName"),
    @NamedQuery(name = "Errorstoreandroid.findByAppVersionCode", query = "SELECT e FROM Errorstoreandroid e WHERE e.appVersionCode = :appVersionCode"),
    @NamedQuery(name = "Errorstoreandroid.findByBrand", query = "SELECT e FROM Errorstoreandroid e WHERE e.brand = :brand"),
    @NamedQuery(name = "Errorstoreandroid.findByPhoneModel", query = "SELECT e FROM Errorstoreandroid e WHERE e.phoneModel = :phoneModel"),
    @NamedQuery(name = "Errorstoreandroid.findByAndroidVersion", query = "SELECT e FROM Errorstoreandroid e WHERE e.androidVersion = :androidVersion")})
public class Errorstoreandroid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "errorStoreAndroidID")
    private Integer errorStoreAndroidID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "errorDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date errorDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "packageName")
    private String packageName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "appVersionName")
    private String appVersionName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "appVersionCode")
    private String appVersionCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "phoneModel")
    private String phoneModel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "androidVersion")
    private String androidVersion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "stackTrace")
    private String stackTrace;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "logCat")
    private String logCat;
    @JoinColumn(name = "riverID", referencedColumnName = "riverID")
    @ManyToOne(optional = false)
    private River river;

    public Errorstoreandroid() {
    }

    public Errorstoreandroid(Integer errorStoreAndroidID) {
        this.errorStoreAndroidID = errorStoreAndroidID;
    }

    public Errorstoreandroid(Integer errorStoreAndroidID, Date errorDate, String packageName, String appVersionName, String appVersionCode, String brand, String phoneModel, String androidVersion, String stackTrace, String logCat) {
        this.errorStoreAndroidID = errorStoreAndroidID;
        this.errorDate = errorDate;
        this.packageName = packageName;
        this.appVersionName = appVersionName;
        this.appVersionCode = appVersionCode;
        this.brand = brand;
        this.phoneModel = phoneModel;
        this.androidVersion = androidVersion;
        this.stackTrace = stackTrace;
        this.logCat = logCat;
    }

    public Integer getErrorStoreAndroidID() {
        return errorStoreAndroidID;
    }

    public void setErrorStoreAndroidID(Integer errorStoreAndroidID) {
        this.errorStoreAndroidID = errorStoreAndroidID;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getLogCat() {
        return logCat;
    }

    public void setLogCat(String logCat) {
        this.logCat = logCat;
    }

    public River getRiver() {
        return river;
    }

    public void setRiver(River river) {
        this.river = river;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (errorStoreAndroidID != null ? errorStoreAndroidID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Errorstoreandroid)) {
            return false;
        }
        Errorstoreandroid other = (Errorstoreandroid) object;
        if ((this.errorStoreAndroidID == null && other.errorStoreAndroidID != null) || (this.errorStoreAndroidID != null && !this.errorStoreAndroidID.equals(other.errorStoreAndroidID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.minisass.data.Errorstoreandroid[ errorStoreAndroidID=" + errorStoreAndroidID + " ]";
    }
    
}
