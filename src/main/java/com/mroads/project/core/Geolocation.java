package com.mroads.project.core;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

// use @Data for setter and getter , @AllArgsConstuctor and NonArgsConstructor from Lombok to make the code cleaner
@Entity
@Table(name = "geolocation")
@NamedQuery(
        name = "org.example.core.Geolocation.findAll",
        query = "SELECT g FROM Geolocation g"
)
public class Geolocation {
    @Id
    @Column(name = "id")
    private String ipAddress;
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

    @CreationTimestamp
    private LocalDateTime creationTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    public Geolocation() {

    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

    public String getiSPName() {
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geolocation that = (Geolocation) o;
        return Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(status, that.status) && Objects.equals(countryCode, that.countryCode) && Objects.equals(region, that.region) && Objects.equals(regionName, that.regionName) && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode) && Objects.equals(timeZone, that.timeZone) && Objects.equals(iSPName, that.iSPName) && Objects.equals(organization, that.organization) && Objects.equals(asNumber, that.asNumber) && Objects.equals(Query, that.Query) && Objects.equals(creationTime, that.creationTime) && Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, status, countryCode, region, regionName, city, zipCode, latitude, longitude, timeZone, iSPName, organization, asNumber, Query, creationTime, updateTime);
    }

}
