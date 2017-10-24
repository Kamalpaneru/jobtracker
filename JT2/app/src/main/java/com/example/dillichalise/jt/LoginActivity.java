package com.example.dillichalise.jt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLogInClick(View v) {
        if (v.getId() == R.id.login) {
            EditText a = (EditText) findViewById(R.id.etuname);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.etpassword);
            String pass = b.getText().toString();
            String password = helper.searchPass(str);

            if (pass.equals(password)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast pass1 = Toast.makeText(LoginActivity.this, "Username and password don't match!", Toast.LENGTH_SHORT);
                pass1.show();
            }
            a.setText("");
            b.setText("");
        }
    }

    public void onRegisterClick(View v) {
        if (v.getId() == R.id.signup) {
            Intent j = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(j);
        }
    }
}








