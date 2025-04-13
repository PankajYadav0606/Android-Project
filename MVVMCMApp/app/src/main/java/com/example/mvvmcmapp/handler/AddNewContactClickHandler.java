package com.example.mvvmcmapp.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmcmapp.MainActivity;
import com.example.mvvmcmapp.model.Contacts;
import com.example.mvvmcmapp.viewmodel.MyViewModel;

public class AddNewContactClickHandler {

    Contacts contacts;

    Context context;

    MyViewModel myViewModel;

    public AddNewContactClickHandler(Contacts contacts, Context context, MyViewModel myViewModel) {
        this.contacts = contacts;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public void onSubmitBtnClicked(View view){
        if (contacts.getName() == null || contacts.getEmail() == null)
            Toast.makeText(context, "Field cannot be blank.", Toast.LENGTH_SHORT).show();

        else {
            Intent intent = new Intent(context, MainActivity.class);

            Contacts c= new Contacts(
                    contacts.getName(),
                    contacts.getEmail(),
                    contacts.getContact_num()
            );
            myViewModel.addNewContacts(c);
            context.startActivity(intent);
        }
    }
}
