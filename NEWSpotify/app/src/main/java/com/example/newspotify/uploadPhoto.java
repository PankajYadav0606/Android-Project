package com.example.newspotify;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class uploadPhoto extends AppCompatActivity {


    private static final int SELECT_PHOTO_REQUEST_CODE = 1;

    private Uri selectedPhotoUri;

    private Bitmap selectedPhotoBitmap;

    TextView toUploadImage;
    Switch toggleSwitchBtn;

    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload_photo);

        toUploadImage=findViewById(R.id.toUploadImage);
        toggleSwitchBtn=findViewById(R.id.toggleBtn);
        registerBtn=findViewById(R.id.registerBtn);


//        toUploadImage.setOnClickListener(view -> uploadPhotograph((ButtonView, isChecked));

//        toggleSwitchBtn.setOnClickListener();

    }



    private void uploadPhotograph(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PHOTO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PHOTO_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            selectedPhotoUri = data.getData();
            try {
                selectedPhotoBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedPhotoUri);

                ImageView selectedPhotoView = findViewById(R.id.selectedPhotoView);
                selectedPhotoView.setImageBitmap(selectedPhotoBitmap);
            } catch (IOException e) {

                Toast.makeText(this, "Failed to load photo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}