package com.example.databinding;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private OnClickHandler onClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Person p1= new Person("Pankaj", "Pankaj@gmail");

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
//        activityMainBinding.setPerson(p1);

        onClickHandler = new OnClickHandler(this);
        activityMainBinding.setOnClickHandle(onClickHandler);

        activityMainBinding.setLifecycleOwner(this);
    }
}