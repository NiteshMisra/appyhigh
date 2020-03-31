package com.news.response;

import com.google.gson.annotations.SerializedName;

public class LocationResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("country")
    private String country;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("region")
    private String region;

    @SerializedName("regionName")
    private String regionName;

    @SerializedName("city")
    private String city;

    @SerializedName("zip")
    private String zip;

    @SerializedName("lat")
    private Float lat;

    @SerializedName("lon")
    private Float lon;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("isp")
    private String isp;

    @SerializedName("org")
    private String org;

    @SerializedName("as")
    private String as;

    @SerializedName("query")
    private String query;

    public LocationResponse(String status, String country, String countryCode, String region, String regionName, String city, String zip, Float lat, Float lon, String timezone, String isp, String org, String as, String query) {
        this.status = status;
        this.country = country;
        this.countryCode = countryCode;
        this.region = region;
        this.regionName = regionName;
        this.city = city;
        this.zip = zip;
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.isp = isp;
        this.org = org;
        this.as = as;
        this.query = query;
    }

    public String getStatus() {
        return status;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getRegion() {
        return region;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getIsp() {
        return isp;
    }

    public String getOrg() {
        return org;
    }

    public String getAs() {
        return as;
    }

    public String getQuery() {
        return query;
    }
}
