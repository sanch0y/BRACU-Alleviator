package com.example.sanchoy.bracualleviator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class student_page extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
    }

    public void fromStudentToFaculty(View v){
        Intent i = new Intent("com.example.sanchoy.bracualleviator.student_to_faculty");
        startActivity(i);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_student_logout) {
            Intent i = new Intent("com.example.sanchoy.bracualleviator.studentLogin_activity");
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
