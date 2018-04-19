package com.example.sanchoy.bracualleviator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;

import java.text.ParseException;


public class facultyLogin_activity extends ActionBarActivity {

    private  static EditText username;
    private  static EditText f_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login_activity);
        username=(EditText)findViewById(R.id.fclt_uname);
        f_password=(EditText)findViewById(R.id.fclty_pass);
    }

    //login button onClick
    public void loginToFacultyPage(View v) {
        String uname = username.getText().toString();
        String pass = f_password.getText().toString();

        ParseUser.logInInBackground(uname, pass, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, com.parse.ParseException e) {
                if (parseUser != null) {
                    Intent i = new Intent("com.example.sanchoy.bracualleviator.faculty_page");
                    startActivity(i);
                    Toast.makeText(facultyLogin_activity.this,"SUCCESSFUL",Toast.LENGTH_LONG).show();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    Toast.makeText(facultyLogin_activity.this," "+e.toString()+" ",Toast.LENGTH_LONG).show();
                    Toast.makeText(facultyLogin_activity.this,"PLEASE TRY AGAIN",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //JOIN HERE onClick. This will take you to the activity: facultyRegister_activity
    public void facultyReg(View v){
        Intent i = new Intent("com.example.sanchoy.bracualleviator.facultyRegister_activity");
        startActivity(i);
    }
}
