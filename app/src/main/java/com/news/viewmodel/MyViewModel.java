package com.news.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.news.repository.Repository;
import com.news.response.LocationResponse;
import com.news.response.NewsResponse;
import com.news.response.NotificationResponse;
import java.util.ArrayList;

public class MyViewModel extends ViewModel {

    private Repository repository;

    public MyViewModel(){
        repository = new Repository();
    }

    public LiveData<ArrayList<String>> getTabs() {
        MutableLiveData<ArrayList<String>> tabs = new MutableLiveData<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("Tab1");
        list.add("Tab2");
        list.add("Tab3");
        list.add("Tab4");
        list.add("Tab5");
        list.add("Tab6");
        list.add("Tab7");
        list.add("Tab8");
        list.add("Tab9");
        list.add("Tab10");
        tabs.postValue(list);
        return tabs;
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
