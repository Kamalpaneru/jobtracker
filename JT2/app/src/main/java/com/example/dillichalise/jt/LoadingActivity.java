package com.example.dillichalise.jt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dillichalise on 7/30/17.
 */

public class LoadingActivity extends AppCompatActivity {
    private static int splashInterval = 2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i= new Intent(LoadingActivity.this, LoginActivity.class);
                startActivity(i);
                this.finish();
            }

            private void finish(){

            }
        },splashInterval);
    }
}
