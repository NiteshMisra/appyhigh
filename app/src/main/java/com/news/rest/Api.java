package com.news.rest;

import com.news.model.notify;
import com.news.response.LocationResponse;
import com.news.response.NewsResponse;
import com.news.response.NotificationResponse;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("json")
    Observable<Response<LocationResponse>> getLocation();

    @GET("top-headlines")
    Observable<Response<NewsResponse>> getNewsList(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @POST("notifications")
    Observable<Response<NotificationResponse>> pushNotification(
            @Header("Authorization") String Authorization,
            @Header("Content-Type") String contentType,
            @Body notify notify
            );

}
