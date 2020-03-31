package com.news.repository;

import androidx.lifecycle.MutableLiveData;
import com.news.model.Contents;
import com.news.model.Data;
import com.news.model.notify;
import com.news.response.LocationResponse;
import com.news.response.NewsResponse;
import com.news.response.NotificationResponse;
import com.news.rest.RetrofitClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private RetrofitClient retrofitClient;

    public Repository(){
        retrofitClient = RetrofitClient.getInstance();
    }

    public MutableLiveData<NewsResponse> fetchNews(String location){
        final MutableLiveData<NewsResponse> newsResponseMutableLiveData = new MutableLiveData<>();
        retrofitClient.getApi().getNewsList(location,"eddad92b139e49ed879fabcc3db48091").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@Nullable Call<NewsResponse> call,@Nullable Response<NewsResponse> response) {
                assert response != null;
                if (response.isSuccessful()){
                    NewsResponse newsResponse = response.body();
                    assert newsResponse != null;
                    if (newsResponse.getTotalResults() > 0){
                        newsResponseMutableLiveData.postValue(newsResponse);
                    }else{
                        newsResponseMutableLiveData.postValue(null);
                    }
                }
            }

            @Override
            public void onFailure(@Nullable Call<NewsResponse> call,@Nullable Throwable t) {
                newsResponseMutableLiveData.postValue(null);
            }
        });
        return newsResponseMutableLiveData;
    }

    public MutableLiveData<LocationResponse> getLocation(){
        final MutableLiveData<LocationResponse> locationResponseMutableLiveData = new MutableLiveData<>();
        retrofitClient.getLocationApi().getLocation().enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(@Nullable Call<LocationResponse> call,@Nullable Response<LocationResponse> response) {
                assert response != null;
                if (response.isSuccessful()){
                    locationResponseMutableLiveData.postValue(response.body());
                }else{
                    locationResponseMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@Nullable Call<LocationResponse> call,@Nullable Throwable t) {
                locationResponseMutableLiveData.postValue(null);
            }
        });
        return locationResponseMutableLiveData;
    }

    public MutableLiveData<NotificationResponse> pushNotify(String title, String body, String url, String imageUrl){
        final MutableLiveData<NotificationResponse> response1 = new MutableLiveData<>();
        try {

            notify notify = new notify("d8eb90bc-4066-4040-98d5-390b5f4aa959",
                    new Contents(body),
                    new Contents(title),
                    new Data(url),
                    new String[]{"All"},
                    imageUrl,
                    "FF0000");

            retrofitClient.pushApi().pushNotification("Basic ZWMwYWYyOTQtZmUxNy00MjJmLThhZWUtY2Y4Y2JiZTRhYmJh",
                    "application/json; charset=utf-8",
                    notify).enqueue(new Callback<NotificationResponse>() {
                @Override
                public void onResponse(@NotNull Call<NotificationResponse> call, @NotNull Response<NotificationResponse> response) {
                    response1.postValue(response.body());
                }

                @Override
                public void onFailure(@NotNull Call<NotificationResponse> call, @NotNull Throwable t) {
                    response1.postValue(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response1;
    }

    public MutableLiveData<NotificationResponse> pushNotifyWithoutImage(String title, String body, String url){
        final MutableLiveData<NotificationResponse> response1 = new MutableLiveData<>();
        try {

            notify notify = new notify("d8eb90bc-4066-4040-98d5-390b5f4aa959",
                    new Contents(body),
                    new Contents(title),
                    new Data(url),
                    new String[]{"All"},
                    "FF0000");

            retrofitClient.pushApi().pushNotification("Basic ZWMwYWYyOTQtZmUxNy00MjJmLThhZWUtY2Y4Y2JiZTRhYmJh",
                    "application/json; charset=utf-8",
                    notify).enqueue(new Callback<NotificationResponse>() {
                @Override
                public void onResponse(@NotNull Call<NotificationResponse> call, @NotNull Response<NotificationResponse> response) {
                    response1.postValue(response.body());
                }

                @Override
                public void onFailure(@NotNull Call<NotificationResponse> call, @NotNull Throwable t) {
                    response1.postValue(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response1;
    }

}
