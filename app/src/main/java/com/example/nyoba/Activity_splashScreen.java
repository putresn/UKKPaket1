package com.example.nyoba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Activity_splashScreen extends AppCompatActivity {

    ImageView splashscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashscreen = (ImageView)findViewById(R.id.iconsplash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Activity_splashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        },2000);

        Animation animsplash = AnimationUtils.loadAnimation(this,R.anim.splashanimation);
        splashscreen.startAnimation(animsplash);

    }
}

