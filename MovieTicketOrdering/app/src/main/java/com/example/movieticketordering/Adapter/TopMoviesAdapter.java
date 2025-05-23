package com.example.movieticketordering.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketordering.GodzillaDetail;
import com.example.movieticketordering.Panda4Detail;
import com.example.movieticketordering.R;
import com.example.movieticketordering.TheFullGuyDetail;
import com.example.movieticketordering.VolleyBallDetail;

import java.util.List;

public class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.TopMoviesHolder> {
    private List<Integer> topMoviesList;
    private Context context;
    public TopMoviesAdapter(List<Integer> topMoviesList,Context context){
        this.topMoviesList = topMoviesList;
        this.context = context;
    }
    @NonNull
    @Override
    public TopMoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topmovies,parent,false);
        return new TopMoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMoviesHolder holder, int position) {
        holder.mImageView.setImageResource(topMoviesList.get(position));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position){
                    case 0:
                        Intent intent1 = new Intent(context, TheFullGuyDetail.class);
                        context.startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(context, GodzillaDetail.class);
                        context.startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(context, VolleyBallDetail.class);
                        context.startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(context, Panda4Detail.class);
                        context.startActivity(intent4);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return topMoviesList.size();
    }

    public class TopMoviesHolder extends RecyclerView.ViewHolder{


        private ImageView mImageView;
        public TopMoviesHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.top_tv);
        }
    }
}
