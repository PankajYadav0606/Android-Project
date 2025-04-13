package com.example.mvvmcmapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmcmapp.databinding.ActivityAddNewContactBinding;
import com.example.mvvmcmapp.handler.AddNewContactClickHandler;
import com.example.mvvmcmapp.model.Contacts;
import com.example.mvvmcmapp.viewmodel.MyViewModel;

public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactBinding binding;
    private AddNewContactClickHandler handler;
    MyViewModel myViewModel;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_contact);

        contacts = new Contacts();

        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_add_new_contact
        );

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        handler = new AddNewContactClickHandler(contacts, this, myViewModel);

        binding.setContact(contacts);
        binding.setClickHandler(handler);
    }
}