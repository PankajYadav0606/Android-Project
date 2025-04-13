package com.example.mvvmcmapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmcmapp.databinding.ActivityMainBinding;
import com.example.mvvmcmapp.handler.MainActivityClickHandler;
import com.example.mvvmcmapp.model.ContactDatabase;
import com.example.mvvmcmapp.model.Contacts;
import com.example.mvvmcmapp.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();
    private MyAdaper myAdaper;
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        handlers = new MainActivityClickHandler(this);
        mainBinding.setClickHandler(handlers);

        RecyclerView recyclerView = mainBinding.recycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        contactDatabase = ContactDatabase.getInstance(this);

        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> newContacts) {
                contactsArrayList.clear();
                for (Contacts c: newContacts){
                    Log.v("TAGGY", c.getEmail());
                    contactsArrayList.add(c);
                }
                myAdaper.notifyDataSetChanged();
            }
        });

        myAdaper = new MyAdaper(contactsArrayList);

        recyclerView.setAdapter(myAdaper);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());

                viewModel.deleteNewContacts(c);
            }
        }).attachToRecyclerView(recyclerView);
    }
}