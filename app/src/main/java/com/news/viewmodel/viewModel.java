package com.news.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.news.repository.Repository;
import com.news.response.LocationResponse;
import com.news.response.NewsResponse;
import com.news.response.NotificationResponse;

public class viewModel extends ViewModel {

    private Repository repository;

    public viewModel(){
        repository = new Repository();
    }

    public LiveData<NewsResponse> getNews(String location){
        return repository.fetchNews(location);
    }

    public LiveData<LocationResponse> getLocation(){
        return repository.getLocation();
    }

    public LiveData<NotificationResponse> push(String title, String body, String url, String imageUrl){
        return repository.pushNotify(title,body,url,imageUrl);
    }

    public LiveData<NotificationResponse> pushWithoutImage(String title, String body, String url){
        return repository.pushNotifyWithoutImage(title,body,url);
    }

}
