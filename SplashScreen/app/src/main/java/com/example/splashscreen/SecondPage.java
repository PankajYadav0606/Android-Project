package com.example.splashscreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondPage extends AppCompatActivity {

    TextView swipeTv;

    ConstraintLayout mainCl;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second_page);

        swipeTv = findViewById(R.id.textView2);
        mainCl = findViewById(R.id.main);

        GestureDetector gestureDetector= new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(@NonNull MotionEvent motionEvent) {
                Toast.makeText(SecondPage.this, "onDown Call", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onShowPress(@NonNull MotionEvent motionEvent) {
                Toast.makeText(SecondPage.this, "onShowPress Call", Toast.LENGTH_SHORT).show();

            }

            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
                Toast.makeText(SecondPage.this, "onSingleTap Call", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onScroll(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
                Toast.makeText(SecondPage.this, "onDown Call", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onLongPress(@NonNull MotionEvent motionEvent) {
                Toast.makeText(SecondPage.this, "onLongPress Call", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onFling(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
                Toast.makeText(SecondPage.this, "onFling Call", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        swipeTv.setOnTouchListener(((V, event) -> gestureDetector.onTouchEvent(event)));

    }
}