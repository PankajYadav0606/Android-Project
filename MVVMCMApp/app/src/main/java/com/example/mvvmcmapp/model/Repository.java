package com.example.mvvmcmapp.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

public class Repository {

    private final ContactDAO contactDAO;
    ExecutorService executorService;
    Handler handler;

    public Repository(Application application) {

        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        executorService = Executors.newSingleThreadExecutor();
       // handler = new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contacts){

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contacts);
            }
        });
    }

    public void deleteContact(Contacts contacts){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contacts);
            }
        });
    }

    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getAllContacts();
    }
}
