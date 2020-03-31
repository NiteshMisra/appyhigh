package com.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.news.R;
import com.news.activity.NewsDetail;
import com.newsapi.model.NewsList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<NewsList> newsLists;
    private Context context;

    public NewsAdapter(ArrayList<NewsList> newsLists, Context context) {
        this.newsLists = newsLists;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_news,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final NewsList updateCurrent = newsLists.get(position);
        if (updateCurrent.getTitle() != null){
            holder.title.setText(updateCurrent.getTitle());
        }else{
            holder.title.setText("");
        }
        if (updateCurrent.getAuthor()!= null){
            holder.author.setText("By "+ updateCurrent.getAuthor());
        }else {
            holder.author.setVisibility(View.GONE);
            holder.postedOn.setPadding(0,10,0,0);
        }
        if (updateCurrent.getPublishedAt() != null){
            try {
                holder.postedOn.setText(getTime(updateCurrent.getPublishedAt()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            holder.postedOn.setVisibility(View.GONE);
        }
        if (updateCurrent.getUrlToImage() != null){
            Glide.with(context).load(updateCurrent.getUrlToImage()).placeholder(R.drawable.ic_bubble_chart_grey).into(holder.imageView);
        }else {
            holder.cardView.setVisibility(View.GONE);
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetail.class);
                intent.putExtra("GSON", new Gson().toJson(updateCurrent));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsLists.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView title,author,postedOn;
        ImageView imageView;
        LinearLayout layout;
        CardView cardView;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            author = itemView.findViewById(R.id.news_author);
            postedOn = itemView.findViewById(R.id.news_time);
            imageView = itemView.findViewById(R.id.news_image);
            layout = itemView.findViewById(R.id.news_layout);
            cardView = itemView.findViewById(R.id.news_card);
        }
    }

    private String getTime(String postedDateTime) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.getDefault());
        sourceFormat.setTimeZone(utc);
        Date postedDate = sourceFormat.parse(postedDateTime);
        Date todayDate = Calendar.getInstance().getTime();

        assert postedDate != null;
        long difference = Math.abs(todayDate.getTime() - postedDate.getTime());
        long min = 1000 * 60;
        long hour = min * 60;
        long days = hour * 24;

        long elapsedDays = difference / days;
        difference = difference % days;

        long elapsedHours = difference / hour;
        difference = difference % hour;

        long elapsedMinutes = difference / min;

        if (elapsedDays == 0){

            if (elapsedHours == 0){

                if (elapsedMinutes == 0)
                    return "Now";
                return elapsedMinutes + " mins ago";
            }else if (elapsedHours == 1){
                return elapsedHours + " hour ago";
            }else{
                return elapsedHours + " hours ago";
            }

        }else if (elapsedDays == 1){
            return "Yesterday";
        }else {
            return elapsedDays + " days ago";
        }
    }

}
