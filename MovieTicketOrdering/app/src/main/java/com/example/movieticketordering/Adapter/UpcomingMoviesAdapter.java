package com.example.movieticketordering.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketordering.FuriosaDetail;
import com.example.movieticketordering.R;

import java.util.List;

public class UpcomingMoviesAdapter extends RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder> {
    private List<Integer> imageList;
    private Context context;
    public UpcomingMoviesAdapter(List<Integer> imageList,Context context){
        this.imageList = imageList;
        this.context = context;
    }
    @NonNull
    @Override
    public UpcomingMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcomingmovies, parent,false);
        return new UpcomingMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingMoviesViewHolder holder, int position) {
        holder.mImageView.setImageResource(imageList.get(position));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        Intent intent1 = new Intent(context, FuriosaDetail.class);
                        context.startActivity(intent1);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class UpcomingMoviesViewHolder extends  RecyclerView.ViewHolder{
        private ImageView mImageView;

        public UpcomingMoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.poster_tv);
        }

    }
}
