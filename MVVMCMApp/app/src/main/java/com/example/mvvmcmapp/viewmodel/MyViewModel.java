package com.example.mvvmcmapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmcmapp.model.Contacts;
import com.example.mvvmcmapp.model.Repository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private Repository myRepository;
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);
    }

    public LiveData<List<Contacts>> getAllContacts() {
        allContacts = myRepository.getAllContacts();
        return allContacts;
    }

    public void addNewContacts(Contacts contacts){
        myRepository.addContact(contacts);
    }

    public void deleteNewContacts(Contacts contacts){
        myRepository.deleteContact(contacts);
    }
}
