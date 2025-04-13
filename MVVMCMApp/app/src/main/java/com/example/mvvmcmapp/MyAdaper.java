package com.example.mvvmcmapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmcmapp.databinding.ItemListBinding;
import com.example.mvvmcmapp.model.Contacts;

import java.util.ArrayList;

public class MyAdaper extends RecyclerView.Adapter<MyAdaper.ContactViewHolder> {

    private ArrayList<Contacts> contactsArrayList;

    public MyAdaper(ArrayList<Contacts> contactsArrayList){
        this.contactsArrayList = contactsArrayList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding itemListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_list, parent, false
        );
        return new ContactViewHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contacts currentContact = contactsArrayList.get(position);
        holder.itemListBinding.setContact(currentContact);

    }

    @Override
    public int getItemCount() {
        return (contactsArrayList != null ? contactsArrayList.size() : 0);
    }

    public void setContactsArrayList(ArrayList<Contacts> contactsArrayList) {
        this.contactsArrayList = contactsArrayList;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        private ItemListBinding itemListBinding;

        public ContactViewHolder(ItemListBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.itemListBinding = contactListItemBinding;
        }
    }
}
