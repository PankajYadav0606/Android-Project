package com.example.databinding;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class OnClickHandler {

    Context context;

    public OnClickHandler(Context context){
        this.context=context;
    }

    public void onButtonClick(View view){
        Toast.makeText(context, "Selsected Button Click......", Toast.LENGTH_SHORT).show();
    }

    public void playBtnHandle(View view){

        context.startService(new Intent(context, MusicService.class));
    }

    public void stopBtnHandle(View view){

        context.stopService(new Intent(context, MusicService.class));
    }
}
