package com.example.databinding;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.databinding.databinding.ActivityMainBinding;
import com.example.databinding.databinding.ActivityViewModelBinding;

public class ViewModel extends AppCompatActivity {


    private OnClickHandler onClickHandler;
    private ActivityViewModelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding= DataBindingUtil.setContentView(this, R.layout.activity_view_model);

        onClickHandler = new OnClickHandler(this);
        binding.setOnClickHandler(onClickHandler);
    }
}