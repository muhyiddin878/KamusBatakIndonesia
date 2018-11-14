package com.example.muhyiddin.kamusbatakindonesia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sound=MediaPlayer.create(this,R.raw.sound);
        sound.start();






        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                onPause();
            }
        }, 3000L); //3000 L = 3 detik

    }
    protected void onPause(){
        super.onPause();
        sound.release();
        finish();
    }

}
