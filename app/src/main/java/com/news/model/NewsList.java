package com.newsapi.model;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class NewsList {

    @Nullable
    @SerializedName("source")
    private Source source;

    @Nullable
    @SerializedName("author")
    private String author;

    @Nullable
    @SerializedName("title")
    private String title;

    @Nullable
    @SerializedName("description")
    private String description;

    @Nullable
    @SerializedName("url")
    private String url;

    @Nullable
    @SerializedName("urlToImage")
    private String urlToImage;

    @Nullable
    @SerializedName("publishedAt")
    private String publishedAt;

    @Nullable
    @SerializedName("content")
    private String content;

    public NewsList(@Nullable Source source, @Nullable String author, @Nullable String title, @Nullable String description, @Nullable String url, @Nullable String urlToImage, @Nullable String publishedAt, @Nullable String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    @Nullable
    public Source getSource() {
        return source;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    @Nullable
    public String getUrlToImage() {
        return urlToImage;
    }

    @Nullable
    public String getPublishedAt() {
        return publishedAt;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public @Nullable  String getSourceId(){
        assert source != null;
        return source.getId();
    }

    public @Nullable String getSourceName(){
        assert source != null;
        return source.getName();
    }

}

class Source {

    @Nullable
    @SerializedName("id")
    private String id;

    @Nullable
    @SerializedName("name")
    private String name;

    public Source(@Nullable String id, @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    @Nullable
    String getId() {
        return id;
    }

    @Nullable
    String getName() {
        return name;
    }
}
