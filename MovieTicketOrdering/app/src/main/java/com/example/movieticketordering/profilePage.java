package com.example.movieticketordering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieticketordering.ui.order.OrderFragment;
import com.example.movieticketordering.ui.print.PrintFragment;

public class profilePage extends AppCompatActivity {

    private PrintFragment.SharedViewModel sharedViewModel;

    static String movieTitle = "";
    private ImageView fallGuy;  // Declare ImageView as a class member
    private ImageView Godzilla;
    private ImageView Panda4;
    private ImageView Volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_page);

        sharedViewModel = new ViewModelProvider(this).get(PrintFragment.SharedViewModel.class);

        // Initialize the ImageView and set it up
        fallGuy = new ImageView(this);
        fallGuy.setImageDrawable(getResources().getDrawable(R.drawable.thefullguy_poster, getTheme()));
        fallGuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.layout, new PrintFragment()).commit();
            }
        });

        Godzilla = new ImageView(this);
        Godzilla.setImageDrawable(getResources().getDrawable(R.drawable.godzilla_poster, getTheme()));
        Godzilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.layout, new PrintFragment()).commit();
            }
        });

        Panda4 = new ImageView(this);
        Panda4.setImageDrawable(getResources().getDrawable(R.drawable.panda4_poster, getTheme()));
        Panda4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.layout, new PrintFragment()).commit();
            }
        });

        Volley = new ImageView(this);
        Volley.setImageDrawable(getResources().getDrawable(R.drawable.volleyball_poster, getTheme()));
        Volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.layout, new PrintFragment()).commit();
            }
        });

        FrameLayout container = findViewById(R.id.ticketContainer);
        container.addView(fallGuy);
        fallGuy.setVisibility(View.GONE);  // Initially hide the ImageView
        container.addView(Godzilla);
        Godzilla.setVisibility(View.GONE);  // Initially hide the ImageView
        container.addView(Panda4);
        Panda4.setVisibility(View.GONE);  // Initially hide the ImageView
        container.addView(Volley);
        Volley.setVisibility(View.GONE);  // Initially hide the ImageView

        sharedViewModel.getTitle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                movieTitle = s;

                if (movieTitle.equals("FallGuy")) {
                    // Show the ImageView if the title matches "FallGuy"
                    fallGuy.setVisibility(View.VISIBLE);
                }
                if (movieTitle.equals("Godzilla")) {
                    // Show the ImageView if the title matches "FallGuy"
                    Godzilla.setVisibility(View.VISIBLE);
                }
                if (movieTitle.equals("Panda4")) {
                    // Show the ImageView if the title matches "FallGuy"
                    Panda4.setVisibility(View.VISIBLE);
                }
                if (movieTitle.equals("Volley")) {
                    // Show the ImageView if the title matches "FallGuy"
                    Volley.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}