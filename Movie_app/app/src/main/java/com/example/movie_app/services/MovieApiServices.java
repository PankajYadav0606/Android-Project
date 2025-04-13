package com.example.movie_app.services;

import com.example.movie_app.Model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiServices {

    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apikey);
}
