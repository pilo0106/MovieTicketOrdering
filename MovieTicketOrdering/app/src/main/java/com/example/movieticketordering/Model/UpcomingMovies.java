package com.example.movieticketordering.Model;

public class UpcomingMovies {
    private int image;
    private String movie;
    public UpcomingMovies(int image, String movie){
        this.image = image;
        this.movie = movie;
    }

    public int getImage() {
        return image;
    }

    public String getMovie() {
        return movie;
    }
}
