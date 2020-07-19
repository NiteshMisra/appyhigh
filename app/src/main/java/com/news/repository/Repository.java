package com.news.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.news.model.Contents;
import com.news.model.Data;
import com.news.model.notify;
import com.news.response.LocationResponse;
import com.news.response.NewsResponse;
import com.news.response.NotificationResponse;
import com.news.rest.RetrofitClient;
import java.util.Objects;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Repository {

    private RetrofitClient retrofitClient;

    public Repository(){
        retrofitClient = RetrofitClient.getInstance();
    }

    public MutableLiveData<NewsResponse> fetchNews(String location){
        final MutableLiveData<NewsResponse> newsResponseMutableLiveData = new MutableLiveData<>();

        retrofitClient.getApi().getNewsList(location,"eddad92b139e49ed879fabcc3db48091")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<NewsResponse>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("response", Objects.requireNonNull(e.getMessage()));
                        newsResponseMutableLiveData.postValue(null);
                    }

                    @Override
                    public void onNext(Response<NewsResponse> response) {
                        if (response.isSuccessful()){
                            newsResponseMutableLiveData.postValue(response.body());
                        }else {
                            newsResponseMutableLiveData.postValue(null);
                        }

                    }
                });

        return newsResponseMutableLiveData;
    }

    public MutableLiveData<LocationResponse> getLocation(){
        MutableLiveData<LocationResponse> locationResponseMutableLiveData = new MutableLiveData<>();
        retrofitClient.getLocationApi().getLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<LocationResponse>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("response", Objects.requireNonNull(e.getMessage()));
                        locationResponseMutableLiveData.postValue(null);
                    }

                    @Override
                    public void onNext(Response<LocationResponse> response) {
                        if (response.isSuccessful()){
                            locationResponseMutableLiveData.postValue(response.body());
                        }else{
                            locationResponseMutableLiveData.postValue(null);
                        }

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
                    notify).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<NotificationResponse>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("response", Objects.requireNonNull(e.getMessage()));
                            response1.postValue(null);
                        }

                        @Override
                        public void onNext(Response<NotificationResponse> response) {
                            if (response.isSuccessful()){
                                response1.postValue(response.body());
                            }else {
                                response1.postValue(null);
                            }

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
                    notify).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<NotificationResponse>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("response", Objects.requireNonNull(e.getMessage()));
                            response1.postValue(null);
                        }

                        @Override
                        public void onNext(Response<NotificationResponse> response) {
                            if (response.isSuccessful()){
                                response1.postValue(response.body());
                            }else {
                                response1.postValue(null);
                            }

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response1;
    }

}
