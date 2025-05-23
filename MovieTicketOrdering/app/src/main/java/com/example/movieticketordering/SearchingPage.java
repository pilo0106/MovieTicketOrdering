package com.example.movieticketordering;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.example.movieticketordering.KeyWord_package.KeyWordSetDBholder;
import com.example.movieticketordering.KeyWord_package.KeyWordSet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchingPage extends AppCompatActivity implements MyAdapter.OnNoteListener {

    RecyclerView recyclerView;
    EditText et_name;
    ImageView btn_search;
    String search_word="";
    KeyWordSetDBholder kdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_page);

        recyclerView = findViewById(R.id.recycler_view);
        kdb = new KeyWordSetDBholder(this, "keyword.db", null, 1);


        // 插入數據

        List<KeyWordSet> general = kdb.getEveryone();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), general, this));

        btn_search= findViewById(R.id.imgBtn_search);
        et_name= findViewById(R.id.editTextText);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_word=et_name.getText().toString();
                //List<KeyWordSet> search_list = kdb.getEveryone();
                List<KeyWordSet> search_list = kdb.searchWord(search_word, kdb);
                recyclerView.setAdapter(new MyAdapter(getApplicationContext(), search_list,SearchingPage.this));
            }
        });
    }

    public void return_btn(View view){
        Bundle back = new Bundle();
        Intent intent = new Intent( SearchingPage.this, MainActivity.class);
        //ntent.putExtra("back", back);
        //intent.putExtra("record", record);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }
    @Override
    public void onNoteClick(int position) {
        MyAdapter adapter = (MyAdapter) recyclerView.getAdapter();
        KeyWordSet clickedItem = adapter.getItemAtPosition(position);
        //Log.d("onNoteClick", "checkC: " + clickedItem );
        //record bundle
        //Bundle record = new Bundle();
        //record.putString("search_word", search_word);

        //
        //Bundle bundle = new Bundle();
        String toId = clickedItem.getId();
        //Log.d("onNoteClick", "checkC: " + toId );
        if("1".equals(toId)) {
            Intent intent = new Intent(this, Panda4Detail.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
        else if ("2".equals(toId)) {
            Intent intent = new Intent(this, VolleyBallDetail.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
        else if("3".equals(toId)){
            Intent intent = new Intent(this, TheFullGuyDetail.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
        else if ("4".equals(toId)) {
            Intent intent = new Intent(this, GodzillaDetail.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
        //bundle.putString("name", toName);
        //Intent intent = new Intent(this, GodzillaDetail.class);
        //intent.putExtra("nameList",bundle);
        //intent.putExtra("record", record);
        //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //startActivity(intent);
    }

}