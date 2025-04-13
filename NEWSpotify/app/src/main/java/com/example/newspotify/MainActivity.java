package com.example.newspotify;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button loginbtn1;
    EditText username_input1,password_input2;
    CheckBox C1,CSS2,Java3,PHP4,Ruby5,Javascript6,Python7,HTML8,MYSQL9;

    //RadioButton rd1,rd2,rd3;
    RadioGroup radio1;
    RadioButton rd1,rd2,rd3;
    Spinner spinner1;
    TextView dob;

    TextView uploadImage;
    private static final int SELECT_PHOTO_REQUEST_CODE = 1;

    private Uri selectedPhotoUri;

    private Bitmap selectedPhotoBitmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        loginbtn1 = findViewById(R.id.btn1);
        username_input1 =findViewById(R.id.username_input);
        password_input2=findViewById(R.id.password_input);
        C1=findViewById(R.id.C);
        CSS2=findViewById(R.id.CSS);
        Java3=findViewById(R.id.Java);
        Python7=findViewById(R.id.Python);
        Ruby5=findViewById(R.id.Ruby);
        Javascript6=findViewById(R.id.Javascript);
        HTML8=findViewById(R.id.HTML);
        MYSQL9=findViewById(R.id.MYSQL);
        PHP4=findViewById(R.id.PHP);
        radio1=findViewById(R.id.radio);
        rd1=findViewById(R.id.r1);
        rd2=findViewById(R.id.r2);
        rd3=findViewById(R.id.r3);
        spinner1=findViewById(R.id.address);
        dob=findViewById(R.id.dob_calender);
        uploadImage=findViewById(R.id.photo);
        setUpSpinner();


        loginbtn1.setOnClickListener(view -> handleLogin());

        dob.setOnClickListener(view -> showDatePickerDialog());

        uploadImage.setOnClickListener(view -> uploadPhotograph());




        username_input1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Enter the UserName", Toast.LENGTH_SHORT).show();
            }
        });

    }




    private void setUpSpinner(){
        String[] cities={
                "Select City" ,"Pune","Mumbai","Nagpur","Nashik","Jaipur","Lucknow","Hyderabad","Ahmedabad","Kolkata","Patna","Agra","Ludhiyana","Thane","Bhopal",
                "Indore","Vadodara"

        };
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,cities);
        adapter.setDropDownViewResource(R.layout.spinner);
        spinner1.setAdapter(adapter);
    }


    private void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> dob.setText(dayOfMonth + "/" + (month1+1) + "/"+ year1),
                year,month,day);

        datePickerDialog.show();


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

    private void handleLogin() {
        String userinput1 = username_input1.getText().toString().trim();
        String userinput2 = password_input2.getText().toString().trim();
        int selectedId = radio1.getCheckedRadioButtonId();
        String selectedCity=spinner1.getSelectedItem().toString();
        String selectedDob=dob.getText().toString();


        ArrayList<String> selectedCourses=new ArrayList<>();
        if(C1.isChecked()) selectedCourses.add(C1.getText().toString());
        if(CSS2.isChecked()) selectedCourses.add(CSS2.getText().toString());
        if(Java3.isChecked()) selectedCourses.add(Java3.getText().toString());
        if(PHP4.isChecked()) selectedCourses.add(PHP4.getText().toString());
        if(Python7.isChecked()) selectedCourses.add(Python7.getText().toString());
        if(HTML8.isChecked()) selectedCourses.add(HTML8.getText().toString());
        if(Javascript6.isChecked()) selectedCourses.add(Javascript6.getText().toString());
        if(MYSQL9.isChecked()) selectedCourses.add(MYSQL9.getText().toString());


        if(userinput1.isEmpty() || userinput2.isEmpty() || selectedCourses.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedOptionsString=String.join(",",selectedCourses);
        RadioButton selectedRadioButton=findViewById(selectedId);
        String selectedGender = selectedRadioButton.getText().toString();

        Intent intent = new Intent(this,MainActivity2.class);
        intent.putExtra("USER_INPUT",userinput1);
        intent.putExtra("PASS_INPUT",userinput2);
        intent.putExtra("COURSES",selectedOptionsString);
        intent.putExtra("GENDER_INPUT", selectedGender);
        intent.putExtra("CITY_INPUT",selectedCity);
        intent.putExtra("BIRTHDATE",selectedDob);

        if (selectedPhotoUri != null) {
            intent.putExtra("PHOTO_URI", selectedPhotoUri.toString());
        } else {
            intent.putExtra("PHOTO_URI", "No photo selected");
            Toast.makeText(this, "No photo selected", Toast.LENGTH_SHORT).show();
        }

        startActivity(intent);
    }

}



