package com.news.response;

import com.google.gson.annotations.SerializedName;
import com.newsapi.model.NewsList;

import java.util.ArrayList;

public class NewsResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private Integer totalResults;

    @SerializedName("articles")
    private ArrayList<NewsList> newsLists;

    public NewsResponse(String status, Integer totalResults, ArrayList<NewsList> newsLists) {
        this.status = status;
        this.totalResults = totalResults;
        this.newsLists = newsLists;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public ArrayList<NewsList> getNewsLists() {
        return newsLists;
    }
}
