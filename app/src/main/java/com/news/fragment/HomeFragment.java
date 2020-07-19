package com.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.gson.Gson;
import com.news.R;
import com.news.activity.NewsDetail;
import com.news.adapter.NewsAdapter;
import com.news.databinding.FragmentHomeBinding;
import com.news.response.LocationResponse;
import com.news.response.NewsResponse;
import com.news.viewmodel.MyViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private com.newsapi.model.NewsList firstNews;
    private MyViewModel myViewModel;
    private FragmentHomeBinding binding;
    private FragmentActivity activity;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = requireActivity();
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myViewModel = new ViewModelProvider(activity).get(MyViewModel.class);

        final AdView mAdView = view.findViewById(R.id.adView);
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recyclerView.setHasFixedSize(false);
        binding.firstNewsLayout.setVisibility(View.GONE);
        binding.recyclerView.setVisibility(View.GONE);

        binding.firstNewsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstNews != null){
                    Intent intent = new Intent(activity, NewsDetail.class);
                    intent.putExtra("GSON", new Gson().toJson(firstNews));
                    startActivity(intent);
                }else {
                    Toast.makeText(activity,"some error occurred",Toast.LENGTH_SHORT).show();
                }
            }
        });

        fetchNews();

        binding.homeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchNews();
            }
        });
    }

    private void fetchNews() {
        binding.homeRefresh.setRefreshing(true);
        myViewModel.getLocation().observe(activity, locationResponse -> {

            if (locationResponse != null){
                Toast.makeText(activity,"Current Location : " + locationResponse.getCountry(),Toast.LENGTH_LONG).show();
                myViewModel.getNews(locationResponse.getCountryCode().toLowerCase()).observe(activity, newsResponse -> {
                    try {

                        if (newsResponse != null && newsResponse.getTotalResults() > 0){
                            ArrayList<com.newsapi.model.NewsList> newsList = newsResponse.getNewsLists();

                            int position = 0;
                            for (com.newsapi.model.NewsList item : newsList){
                                if (item.getUrlToImage() != null && item.getTitle() != null){
                                    Glide.with(activity).load(item.getUrlToImage()).placeholder(R.drawable.ic_bubble_chart_grey).into(binding.image);
                                    if (item.getAuthor() != null){
                                        binding.author.setText(("By "+ item.getAuthor()));
                                    }else {
                                        binding.author.setVisibility(View.GONE);
                                    }
                                    binding.title.setText(item.getTitle());
                                    firstNews = item;
                                    break;
                                }else{
                                    position++;
                                }
                            }

                            ArrayList<com.newsapi.model.NewsList> restNewsList = new ArrayList<>();
                            for (int i =0 ;i<newsList.size(); i++){
                                if (i != position && Objects.requireNonNull(!newsList.get(i).getUrl().contains("timesofindia"))){
                                    restNewsList.add(newsList.get(i));
                                }
                            }
                            NewsAdapter newsAdapter = new NewsAdapter(restNewsList,activity);
                            binding.recyclerView.setAdapter(newsAdapter);
                            newsAdapter.notifyDataSetChanged();
                            binding.homeRefresh.setRefreshing(false);
                            binding.firstNewsLayout.setVisibility(View.VISIBLE);
                            binding.recyclerView.setVisibility(View.VISIBLE);
                        }else {
                            binding.homeRefresh.setRefreshing(false);
                            Toast.makeText(activity,"Some error occurred, Please try aging later",Toast.LENGTH_LONG).show();
                        }

                    }catch (NullPointerException e){
                        Log.e("mainActivity", Objects.requireNonNull(e.getMessage()));
                    }
                });
            }else{
                binding.homeRefresh.setRefreshing(false);
                Toast.makeText(activity,"Some error occurred, Please try aging later",Toast.LENGTH_LONG).show();
            }

        });
    }

}