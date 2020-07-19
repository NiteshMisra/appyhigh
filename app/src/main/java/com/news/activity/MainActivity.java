package com.news.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.news.R;
import com.news.adapter.TabAdapter;
import com.news.databinding.ActivityMainBinding;
import com.news.fragment.HomeFragment;
import com.news.viewmodel.MyViewModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabAdapter.TabClickListener {

    private int selectedTabPosition = 0;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.tabsRcv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        binding.tabsRcv.setHasFixedSize(true);

        myViewModel.getTabs().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                tabAdapter = new TabAdapter(strings,selectedTabPosition,MainActivity.this);
                binding.tabsRcv.setAdapter(tabAdapter);
                tabAdapter.notifyDataSetChanged();
            }
        });


        addFragment();

    }

    public void addFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.news_container, HomeFragment.newInstance())
                .commit();
    }

    @Override
    public void onClick(int position) {
        tabAdapter.notifyItemChanged(selectedTabPosition);
        selectedTabPosition = position;

        addFragment();
    }
}
