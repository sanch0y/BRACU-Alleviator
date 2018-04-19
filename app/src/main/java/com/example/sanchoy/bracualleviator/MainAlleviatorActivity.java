package com.example.sanchoy.bracualleviator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;



public class MainAlleviatorActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alleviator);
    }

    public void facultyBtn(View v){
        Intent i = new Intent("com.example.sanchoy.bracualleviator.facultyLogin_activity");
        startActivity(i);
    }

    public void studentBtn(View v){
        Intent i = new Intent("com.example.sanchoy.bracualleviator.studentLogin_activity");
        startActivity(i);
    }

    public void adminLogin(View v){
        Intent i = new Intent("com.example.sanchoy.bracualleviator.admin_login");
        startActivity(i);
    }



}
