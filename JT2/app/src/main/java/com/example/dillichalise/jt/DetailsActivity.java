package com.example.dillichalise.jt;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String form_id = getIntent().getStringExtra("FORM_ID");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(DetailsActivity.this, MainActivity.class);
                DetailsActivity.this.startActivity(mainIntent);
                DetailsActivity.this.finish();
            }
        }, 100);

    }
}
