package com.example.mvvmcmapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contacts {



    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Contact_Id")
    private int id;
    @ColumnInfo(name = "Contact_Name")
    private String name;

    @ColumnInfo(name = "Contact_Email")
    private String email;

    @ColumnInfo(name = "Contact_Num")
    private String contact_num;

    public Contacts(String name, String email, String contact_num) {
        this.name = name;
        this.email = email;
        this.contact_num = contact_num;
    }

    public Contacts() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_num() {
        return contact_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }


}
