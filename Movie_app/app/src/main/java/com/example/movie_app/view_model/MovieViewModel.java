package com.example.movie_app.view_model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movie_app.MainActivity;
import com.example.movie_app.Model.MovieRepository;
import com.example.movie_app.Model.Result;

import java.util.List;

public class MovieViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel(Application application) {
        super();
        this.movieRepository = new MovieRepository(application);
    }

    //Live Data
    public MutableLiveData<List<Result>> getAllMovies(){
        return  movieRepository.getMutableLiveData();
    }
}
