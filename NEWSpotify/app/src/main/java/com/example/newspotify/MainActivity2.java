package com.example.newspotify;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {
    TextView username1,password1,courses,gender1,address1,dob1;

    ImageView uploadedPhoto;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        username1=findViewById(R.id.username);
        password1=findViewById(R.id.password);
        courses=findViewById(R.id.course);
        gender1=findViewById(R.id.gender);
        address1=findViewById(R.id.address);
        dob1=findViewById(R.id.birthdate);
        uploadedPhoto=findViewById(R.id.upload);


        String userValue=getIntent().getStringExtra("USER_INPUT");
        String passValue=getIntent().getStringExtra("PASS_INPUT");
        String CoursesValue=getIntent().getStringExtra("COURSES");
        String GenderValue=getIntent().getStringExtra("RADIO_INPUT");
        String AddressValue=getIntent().getStringExtra("CITY_INPUT");
        String BirthValue=getIntent().getStringExtra("BIRTHDATE");
        String photoUriString=getIntent().getStringExtra("PHOTO_URI");


        username1.setText(getFallbackText(userValue,"No Username provided"));
        password1.setText(getFallbackText(passValue,"No Password provided"));
        courses.setText((getFallbackText(CoursesValue,"No courses selected")));
        gender1.setText(getFallbackText(GenderValue,"No gender selected"));
        address1.setText(getFallbackText(AddressValue,"No city selected"));
        dob1.setText(getFallbackText(BirthValue, "No Date selected"));

        //Display upload photo

        if (photoUriString != null){
            try {
                Uri photoUri=Uri.parse(photoUriString);
                InputStream inputStream=getContentResolver().openInputStream(photoUri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                uploadedPhoto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                uploadedPhoto.setImageResource(R.drawable.img);
            }
        }else {
            uploadedPhoto.setImageResource(R.drawable.img);;
        }

    }

    private String getFallbackText(String input, String fallback) {
        return input !=null && !input.isEmpty()?input:fallback;
    }
}