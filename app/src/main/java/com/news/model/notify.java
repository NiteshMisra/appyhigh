package com.news.model;

import com.google.gson.annotations.SerializedName;

public class notify {

    @SerializedName("app_id")
    private String appId;

    @SerializedName("contents")
    private Contents contents;

    @SerializedName("headings")
    private Contents headings;

    @SerializedName("data")
    private Data data;

    @SerializedName("included_segments")
    private String[] segments;

    @SerializedName("large_icon")
    private String image;

    @SerializedName("android_accent_color")
    private String colour;


    public notify(String appId, Contents contents, Contents headings, Data data, String[] segments, String image, String colour) {
        this.appId = appId;
        this.contents = contents;
        this.headings = headings;
        this.data = data;
        this.segments = segments;
        this.image = image;
        this.colour = colour;
    }

    public notify(String appId, Contents contents, Contents headings, Data data, String[] segments, String colour) {
        this.appId = appId;
        this.contents = contents;
        this.headings = headings;
        this.data = data;
        this.segments = segments;
        this.colour = colour;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public void setHeadings(Contents headings) {
        this.headings = headings;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setSegments(String[] segments) {
        this.segments = segments;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getAppId() {
        return appId;
    }

    public Contents getContents() {
        return contents;
    }

    public Contents getHeadings() {
        return headings;
    }

    public Data getData() {
        return data;
    }

    public String[] getSegments() {
        return segments;
    }

    public String getImage() {
        return image;
    }

    public String getColour() {
        return colour;
    }
}
