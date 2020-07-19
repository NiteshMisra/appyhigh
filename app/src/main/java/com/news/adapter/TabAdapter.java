package com.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.news.R;
import java.util.ArrayList;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.TabVH> {

    private ArrayList<String> tabList;
    private int selectedTabPos;
    private TabClickListener tabClickListener;

    public TabAdapter(ArrayList<String> tabList, int selectedTabPos, TabClickListener tabClickListener){
        this.selectedTabPos = selectedTabPos;
        this.tabList = tabList;
        this.tabClickListener = tabClickListener;
    }

    @NonNull
    @Override
    public TabVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_tabs,parent,false);
        return new TabVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabVH holder, final int position) {

        holder.tabName.setText(tabList.get(position));

        if (selectedTabPos == position){
            holder.tabDot.setVisibility(View.VISIBLE);
        }else {
            holder.tabDot.setVisibility(View.GONE);
        }

        holder.tabLayout.setOnClickListener(v -> {
            selectedTabPos = position;
            notifyItemChanged(selectedTabPos);
            tabClickListener.onClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return tabList.size();
    }

    public static class TabVH extends RecyclerView.ViewHolder {

        TextView tabName;
        ImageView tabDot;
        LinearLayout tabLayout;

        TabVH(View view){
            super(view);
            tabName = view.findViewById(R.id.name);
            tabDot = view.findViewById(R.id.dot);
            tabLayout = view.findViewById(R.id.layout);

        }

    }

    public interface TabClickListener {

        void onClick(int position);

    }

}
