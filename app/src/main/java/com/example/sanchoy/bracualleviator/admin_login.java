package com.example.sanchoy.bracualleviator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class admin_login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }


    public void goToAdminPage(View view){
        Intent intent = new Intent("com.example.sanchoy.bracualleviator.admin_page");
        startActivity(intent);
    }

}
