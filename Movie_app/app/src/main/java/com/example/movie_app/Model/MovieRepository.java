package com.example.movie_app.Model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.movie_app.R;
import com.example.movie_app.services.MovieApiServices;
import com.example.movie_app.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private List<Result> movies = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<List<Result>>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {

        MovieApiServices movieApiServices = RetrofitInstance.getServices();
        Call<Result> call = movieApiServices
                .getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                //Success Code
                Result result = response.body();

                if (result != null && result.getResults() != null) {
                    movies = result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {

            }
        });
        
        return mutableLiveData;
    }
}
