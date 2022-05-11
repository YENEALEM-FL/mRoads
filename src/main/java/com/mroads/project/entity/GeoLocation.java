package com.mroads.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "geo_location")

public class GeoLocation {
	//Note: you can add @NotNull, @Size(), and other anotations according to your coding preference
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "status")
    private String status;
    @Column(name = "countryCode")
    private String countryCode;
    @Column(name="region")
    private String region;
    @Column(name="regionName")
    private String regionName;
    @Column(name="city")
    private String city;
    @Column(name="zipCode")
    private String zipCode;
    @Column(name="latitude")
    private double latitude;
    @Column(name ="longitude")
    private double longitude;
    @Column(name="timeZone")
    private String timeZone;
    @Column(name="ispName")
    private String iSPName;
    @Column(name="organization")
    private String organization;
    @Column(name="asNumber")
    private String asNumber;
    @Column(name="query")
    private String Query;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getISPName() {
        return iSPName;
    }

    public void setiSPName(String iSPName) {
        this.iSPName = iSPName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAsNumber() {
        return asNumber;
    }

    public void setAsNumber(String asNumber) {
        this.asNumber = asNumber;
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }
}
