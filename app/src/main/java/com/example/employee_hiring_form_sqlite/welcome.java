package com.example.employee_hiring_form_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class welcome extends AppCompatActivity {
 TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txt = (TextView) findViewById(R.id.welcometxt);

        Intent i = getIntent();
        String user = i.getStringExtra("useremail");
        txt.setText("Welcome "+ user);
    }
}