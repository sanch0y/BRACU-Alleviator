package com.example.sanchoy.bracualleviator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseObject;


public class admin_page extends ActionBarActivity {

    /*1*/String getInitial;
    /*2*/String getWorkingDay;
    /*3*/String getFreeTiming;

    /*1*/EditText fcltyIntl;
    /*2*/EditText fcltyWorkingdays;
    /*3*/EditText fcltyFreeTiming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        /*1*/fcltyIntl = (EditText)findViewById(R.id.fclty_intl);
        /*2*/fcltyWorkingdays = (EditText)findViewById(R.id.fclty_workingdays);
        /*3*/fcltyFreeTiming = (EditText)findViewById(R.id.fclty_free_timing);
    }//onCreate Ends

    public void saveFreetimingInParse(View view){
    /*1*/
        getInitial = fcltyIntl.getText().toString();
    /*2*/
        getWorkingDay = fcltyWorkingdays.getText().toString();
    /*3*/
        getFreeTiming = fcltyFreeTiming.getText().toString();
        String [] splittedFreeTiming = getFreeTiming.split(",");

        //Faculty_free_timing Table
        for(int i = 0; i<splittedFreeTiming.length; i++) {
            ParseObject facultyFreeTiming = new ParseObject("Faculty_free_timing");
            facultyFreeTiming.put("faculty_intl",getInitial);
            facultyFreeTiming.put("faculty_workingDay",getWorkingDay);
            facultyFreeTiming.put("faculty_freeTiming",splittedFreeTiming[i]);
            facultyFreeTiming.saveInBackground();
        }//for loop ends
        //Faculty_free_timing Table
    }//onClick ends

}
