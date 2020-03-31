package com.news.response;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;

public class NotificationResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("recipients")
    private Integer recipients;

    @Nullable
    @SerializedName("external_id")
    private String externalId;

    @Nullable
    @SerializedName("errors")
    private ArrayList<String> errors;

    public NotificationResponse(String id, Integer recipients, @Nullable String externalId, @Nullable ArrayList<String> errors) {
        this.id = id;
        this.recipients = recipients;
        this.externalId = externalId;
        this.errors = errors;
    }

    public String getId() {
        return id;
    }

    public Integer getRecipients() {
        return recipients;
    }

    @Nullable
    public String getExternalId() {
        return externalId;
    }

    @Nullable
    public ArrayList<String> getErrors() {
        return errors;
    }
}
