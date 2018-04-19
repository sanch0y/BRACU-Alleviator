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


public class studentLogin_activity extends ActionBarActivity {

    private  static EditText id;
    private  static EditText s_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login_activity);
        id=(EditText)findViewById(R.id.std_id);
        s_password=(EditText)findViewById(R.id.std_pass);
    }

    //login button onClick
    public void loginToStudentPage(View v) {
        String identity = id.getText().toString();
        String pass = s_password.getText().toString();
        ParseUser.logInInBackground(identity, pass, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, com.parse.ParseException e) {
                if (parseUser != null) {
                    Intent i = new Intent("com.example.sanchoy.bracualleviator.student_page");
                    startActivity(i);
                    Toast.makeText(studentLogin_activity.this,"SUCCESSFUL",Toast.LENGTH_LONG).show();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    Toast.makeText(studentLogin_activity.this," "+e.toString()+" ",Toast.LENGTH_LONG).show();
                    Toast.makeText(studentLogin_activity.this,"PLEASE TRY AGAIN",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //REGISTER onClick. This will take you to the activity: facultyRegister_activity
    public void studentReg(View v) {
        Intent i = new Intent("com.example.sanchoy.bracualleviator.stdRegister_activity");
        startActivity(i);
    }

}
