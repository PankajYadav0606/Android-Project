package com.example.movie_app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.movie_app.Model.Movies;
import com.example.movie_app.databinding.ActivityMainBinding;
import com.example.movie_app.view_model.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movies> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActivityMainBinding binding;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        getPopularMovies();
    }

    private void getPopularMovies(){
        viewModel.getAllMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> moviesFromLiveData) {
                movies = (ArrayList<Movies>) moviesFromLiveData;
                displayMovieInRecyclerView();
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void displayMovieInRecyclerView(){
        recyclerView = binding.rectangles;
        movieAdapter = new MovieAdapter(this, movies);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        movieAdapter.notifyDataSetChanged();
    }

}