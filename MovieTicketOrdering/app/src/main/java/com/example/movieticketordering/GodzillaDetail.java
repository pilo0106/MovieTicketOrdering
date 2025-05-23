package com.example.movieticketordering;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieticketordering.ui.order.OrderFragment;

public class GodzillaDetail extends AppCompatActivity implements View.OnClickListener {

    public static class SharedViewModel extends ViewModel {
        private static MutableLiveData<String> movieTitle = new MutableLiveData<>();

        public static void setTicketTitle(String title) {
            movieTitle.setValue(title);
        }

        public LiveData<String> getTitle() {
            return movieTitle;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_godzilla_detail);
        ImageView backbutton =findViewById(R.id.backbtn);
        ImageView poster =findViewById(R.id.movieposter);
        poster.setOnClickListener(this);
        backbutton.setOnClickListener(this);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        if(view.getId()==R.id.backbtn){
            /*Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);*/
            onBackPressed();
        }
        if(view.getId()==R.id.button) {
            GodzillaDetail.SharedViewModel.setTicketTitle("Godzilla");
            getSupportFragmentManager().beginTransaction().replace(R.id.layout, new OrderFragment()).commit();
        }
        if (view.getId() == R.id.movieposter){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("https://www.youtube.com/watch?v=lV1OOlGwExM&pp=ygUIZ29kemlsbGE%3D"));
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed(); // 返回到上一個 Activity 或 Fragment
    }

}