package com.example.mvvmcmapp.model;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;

@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDAO getContactDAO();
    private static ContactDatabase dbInstance;

    public static synchronized ContactDatabase getInstance(Context context){
        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contact,db"
            ).fallbackToDestructiveMigration().build();
        }
        return dbInstance;
    }
}
