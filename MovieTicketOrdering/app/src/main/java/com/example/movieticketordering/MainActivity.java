package com.example.movieticketordering;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.movieticketordering.Adapter.TopMoviesAdapter;
import com.example.movieticketordering.Adapter.UpcomingMoviesAdapter;
import com.example.movieticketordering.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private List<Image> imageList;
    private ImageAdapter adapter;
    private Handler sliderHandler = new Handler();
    private RecyclerView UpcomingMoviesRecyclerView, TopMoviesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        TopMoviesRecyclerView = findViewById(R.id.TopMoviesRecyclerView);
        TopMoviesRecyclerView.setHasFixedSize(true);
        TopMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Integer> topMovieList = new ArrayList<>();
        topMovieList.add(R.drawable.thefullguy_poster);
        topMovieList.add(R.drawable.godzilla_poster);
        topMovieList.add(R.drawable.volleyball_poster);
        topMovieList.add(R.drawable.panda4_poster);

        TopMoviesAdapter topMoviesAdapter = new TopMoviesAdapter(topMovieList, this);
        TopMoviesRecyclerView.setAdapter(topMoviesAdapter);

        UpcomingMoviesRecyclerView = findViewById(R.id.UpcomingMoviesrecyclerView);
        UpcomingMoviesRecyclerView.setHasFixedSize(true);
        UpcomingMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Integer> upcomingImageList = new ArrayList<>();
        upcomingImageList.add(R.drawable.furiosa);
        upcomingImageList.add(R.drawable.apes);
        upcomingImageList.add(R.drawable.garfield);
        upcomingImageList.add(R.drawable.moana2);

        UpcomingMoviesAdapter upcomingMoviesAdapter = new UpcomingMoviesAdapter(upcomingImageList, this);
        UpcomingMoviesRecyclerView.setAdapter(upcomingMoviesAdapter);

        viewPager2 = findViewById(R.id.viewPager2);
        imageList = new ArrayList<>();
        imageList.add(new Image(R.drawable.godzilla));
        imageList.add(new Image(R.drawable.thefallguy));
        imageList.add(new Image(R.drawable.thegentlemen));
        imageList.add(new Image(R.drawable.dune2));
        imageList.add(new Image(R.drawable.panda4));

        adapter = new ImageAdapter(imageList, viewPager2);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.14f);
            }
        });

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 处理 ImageView2 的点击事件
                Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent2);
            }
        });

        ImageView imageViewProfile = findViewById(R.id.profilebtn);
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this, profilePage.class);
                startActivity(intent3);
            }
        });

        viewPager2.setPageTransformer(transformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000);
            }
        });

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 處理ImageView的點擊事件
                Intent intent = new Intent(MainActivity.this, SearchingPage.class);
                startActivity(intent);
            }
        });
    }


    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
    }
}
