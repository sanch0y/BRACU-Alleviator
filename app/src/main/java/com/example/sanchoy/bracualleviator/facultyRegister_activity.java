package com.example.sanchoy.bracualleviator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class facultyRegister_activity extends ActionBarActivity {

    Spinner sp;
    ArrayAdapter<CharSequence> arrayAdapter;
    EditText fcName;
    EditText fcInitial;
    EditText fcEmail;
    EditText fcPass;
    EditText fcCourse;
    String temp_initial;
    String temp_courses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_register_activity);
        sp =(Spinner) findViewById(R.id.spnr);
        arrayAdapter = ArrayAdapter.createFromResource(this,R.array.departments,android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arrayAdapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),"  Selected Department is "+parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }/*onItemSelected method ENDS*/)/*setOnItemSelectedListener parameter ENDS*/;

        fcName = (EditText)findViewById(R.id.facultyName);
        fcInitial = (EditText)findViewById(R.id.facultyIn);
        fcEmail = (EditText)findViewById(R.id.facultyEmail);
        fcPass = (EditText)findViewById(R.id.facultyPass);
        fcCourse = (EditText)findViewById(R.id.facultyCourses);
    }//onCreate ENDS


    public void facultySubmit(View view){
        String facName = fcName.getText().toString();
        String facInitial = fcInitial.getText().toString();
        temp_initial = facInitial;//temporily storing faculty initials for "Faculty_with_courses" class
        String facEmail = fcEmail.getText().toString();
        String facPass = fcPass.getText().toString();
        String facDept = sp.getSelectedItem().toString();
        String facCourse = fcCourse.getText().toString();
        temp_courses = facCourse;//temporily storing faculty initials for "Faculty_with_courses" class
        //Splitting the string facCourse and storing in the array
        String [] fc = facCourse.split(",");

        //faculty register
        ParseUser userFaculty = new ParseUser();
        userFaculty.setUsername(facInitial);
        userFaculty.setPassword(facPass);
        userFaculty.setEmail(facEmail);
        userFaculty.put("faculty_name",facName);
        userFaculty.put("faculty_department",facDept);
        for(int i = 0; i<fc.length; i++) {
            userFaculty.addUnique("Taken_Courses",fc[i]);
        }
        userFaculty.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(facultyRegister_activity.this,"Congratulation",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(facultyRegister_activity.this," "+e.getMessage()+" ",Toast.LENGTH_LONG).show();
                }
            }
        });
        //faculty register

        //Faculty_with_courses table
        String [] tmp_course = temp_courses.split(",");
        for(int i = 0; i<tmp_course.length; i++) {
        ParseObject facultyWithCourses = new ParseObject("Faculty_with_courses");
            facultyWithCourses.put("faculty_initial", temp_initial);
            facultyWithCourses.put("faculty_courses", tmp_course[i]);
            facultyWithCourses.saveInBackground();
        }
        //Faculty_with_courses table

    }//onClick ends


}
