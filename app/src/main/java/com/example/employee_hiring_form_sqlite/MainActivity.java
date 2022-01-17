package com.example.employee_hiring_form_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText Rusername,Remail,Rpass,Rconfirmpass,Rexpsalary,Rhandle,Rspeciality;
    Button Rbtn;
    Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rusername = (EditText) findViewById(R.id.rusername);
        Remail = (EditText) findViewById(R.id.remail);
        Rpass = (EditText) findViewById(R.id.rpassword);
        Rconfirmpass = (EditText) findViewById(R.id.rconfirmpass);
        Rexpsalary = (EditText) findViewById(R.id.rexpsalary);
        Rhandle = (EditText) findViewById(R.id.rprojects);
        Rspeciality = (EditText) findViewById(R.id.rspeciality);
        Rbtn = (Button) findViewById(R.id.rbtn);
        db = new Db(MainActivity.this);


        Rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rusername = Rusername.getText().toString();
                String remail = Remail.getText().toString();
                String rpassword = Rpass.getText().toString();
                String rconfirmpass = Rconfirmpass.getText().toString();
                int rexpsalary = Integer.parseInt(Rexpsalary.getText().toString());
                int rprojects  = Integer.parseInt(Rhandle.getText().toString());
                String rspeciality = Rspeciality.getText().toString();

                if (rpassword.equals(rconfirmpass)) {

                    boolean email = db.checkemail(remail);
                    if (email == true) {
                        Toast.makeText(MainActivity.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                    } else {
                        if (rexpsalary < 25000) {
                            Toast.makeText(MainActivity.this, "Salary must be 25000", Toast.LENGTH_SHORT).show();
                        } else if (rexpsalary > 75000) {
                            Toast.makeText(MainActivity.this, "Salary must be under 75000", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean successfullyinserted = db.Insertrecord(rusername, remail, rpassword, rexpsalary, rprojects, rspeciality);
                            if (successfullyinserted == true) {
                                Toast.makeText(MainActivity.this, "Data Insert Successfully", Toast.LENGTH_SHORT).show();
                                Intent i =new Intent(MainActivity.this,Login.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}