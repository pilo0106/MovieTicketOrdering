package com.example.movieticketordering;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketordering.KeyWord_package.KeyWordSet;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<KeyWordSet> general_list;
    private OnNoteListener mOnNoteListener;
    /*
    public MyAdapter(Context context, List<General> general_list){
        this.context = context;
        this.general_list = general_list;
    }

     */
    public MyAdapter(Context context, List<KeyWordSet> general_list, OnNoteListener onNoteListener){
        this.context = context;
        this.general_list = general_list;
        this.mOnNoteListener = onNoteListener;
        Log.d("MyAdapter", "check: " + this.general_list );
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.info_list,parent,false);
        return new MyViewHolder(item, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(general_list.get(position).getName());
        holder.brief.setText(general_list.get(position).getKeyword());
    }

    @Override
    public int getItemCount() {
        return general_list.size();
    }

    public KeyWordSet getItemAtPosition(int position) {
        return general_list.get(position);
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
