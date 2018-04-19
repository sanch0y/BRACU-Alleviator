package com.example.sanchoy.bracualleviator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class student_to_faculty extends ActionBarActivity   {
    String userName;//for setting student id at the top
    String studentRequestStrings;//to send student request massage to parse
    String selectedDept;
    String selectedInitial;
    String selectedCourse;
    String selectedTiming;
    String selectedDay;
    String tmpInitialForFreeTiming;//this variable is for temporary storage of faculty initial from initial spinner. so that we can use this variable inside weekdays spinner
    //Spinners
    /*1*/Spinner department;
    /*2*/Spinner timing;
    /*3*/Spinner initial;
    /*4*/Spinner course;
    /*5*/Spinner weekdays;
    //Adapters
    /*1*/ArrayAdapter<CharSequence> arrayAdapter;//department
    /*2*/ParseQueryAdapter<ParseObject> timingAdapter;//timing
    /*3*/ParseQueryAdapter<ParseUser> adapter;//initial
    /*4*/ParseQueryAdapter<ParseObject> courseAdapter;//course
    /*5*/ArrayAdapter<CharSequence> weekdaysAdapter;//weekdays
    //EditText
    EditText et;
    EditText fcltyRmSts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_to_faculty);
        //finding spinners by their xml id
        /*1*/department = (Spinner)findViewById(R.id.spinnerForDepartment);
        /*2*/timing = (Spinner)findViewById(R.id.spinnerforTiming);
        /*3*/initial = (Spinner)findViewById(R.id.spinnerForInitial);
        /*4*/course = (Spinner)findViewById(R.id.spinnerForCourse);
        /*5*/weekdays = (Spinner)findViewById(R.id.spinnerForDay);
        /*EditTExt*/
        et = (EditText)findViewById(R.id.currentStudent);
        fcltyRmSts = (EditText)findViewById(R.id.faculty_room_status);


        //getting current student id
        ParseUser currentUser = ParseUser.getCurrentUser();
        userName = currentUser.getUsername();
        et.setText(userName);//setting student id at the top

        /*1#3###############################################################################################################*/

        /*1*///department array adapter
        arrayAdapter = ArrayAdapter.createFromResource(this,R.array.departments,android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(arrayAdapter);

        /*1*///department setOnItemSelectedListener
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               /*1*/Toast.makeText(getBaseContext(), "  Selected Department is " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                //getting the selected item from the department spinner
               /*1*/selectedDept = department.getSelectedItem().toString();

                /*3*///Start
                //query adapter for spinner named initial
                ParseQueryAdapter.QueryFactory<ParseUser> factory = new ParseQueryAdapter.QueryFactory<ParseUser>() {
                    public ParseQuery<ParseUser> create() {
                        ParseQuery<ParseUser> query = ParseUser.getQuery();
                        query.whereEqualTo("faculty_department", selectedDept);
                        query.orderByAscending("username");
                        return query;
                    }
                };//query adapter for initial Ends
                // Pass the factory into the ParseQueryAdapter's constructor.
                adapter = new ParseQueryAdapter<ParseUser> (student_to_faculty.this, factory);
                adapter.setTextKey("username");
                //setting the adapter
                initial.setAdapter(adapter);
                initial.setSelection(1);
                /*3 End*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }/*1 onItemSelected method ENDS*/);/*department setOnItemSelectedListener parameter 1 ENDS*/
        /*1 End*/



        /*3#4#5##############################################################################################################*/

        /*3*///initial setOnItemSelectedListener
        initial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ParseUser theSelectedUser = adapter.getItem(position);
                /*3*/Toast.makeText(parent.getContext(), " Selected initial " + theSelectedUser.get("username") + " ", Toast.LENGTH_LONG).show();
                //getting the selected item from the initial spinner
                /*3*/tmpInitialForFreeTiming = selectedInitial = theSelectedUser.get("username").toString();

                //*****************************************************************************************************************
                /*5*/  weekdays.setAdapter(weekdaysAdapter);/*SETTING WEEKDAYS ADAPTER TO LOAD IT AFTER initial SPINNERr*//*5 ENDS*/
                //*****************************************************************************************************************

                /*4*///Start
                //query adapter for spinner named course
                ParseQueryAdapter.QueryFactory<ParseObject> fctry = new ParseQueryAdapter.QueryFactory<ParseObject>() {
                    public ParseQuery create() {
                        ParseQuery query = new ParseQuery("Faculty_with_courses");
                        query.whereEqualTo("faculty_initial", selectedInitial);
                        query.orderByAscending("faculty_courses");
                        return query;
                    }
                };//query adapter for course Ends
                // Pass the factory into the ParseQueryAdapter's constructor.
                courseAdapter = new ParseQueryAdapter<ParseObject> (student_to_faculty.this, fctry);
                courseAdapter.setTextKey("faculty_courses");
                //setting the adapter
                course.setAdapter(courseAdapter);
                course.setSelection(1);
                ///*4 End*/
            }//On Item selected
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        }/*OnItemSelected method ENDS*/);/*initial setOnItemSelectedListener parameter 3 ENDS*/
       /*3 end*/

        /*4################################################################################################################*/

        /*4*///course setOnItemSelectedListener
        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ParseObject theSelectedCourse = courseAdapter.getItem(position);
                selectedCourse = theSelectedCourse.getString("faculty_courses");
                Toast.makeText(parent.getContext(), " Selected course " +  selectedCourse, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }/*OnItemSelected method ENDS*/);/*course setOnItemSelectedListener parameter 4 ENDS*/

/*5################################################################################################################*/

        /*5*///weekdays array adapter
        weekdaysAdapter = ArrayAdapter.createFromResource(this,R.array.week_days,android.R.layout.simple_spinner_dropdown_item);
        weekdaysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //we are not setting weekdays adapter here. we are setting weekdays adapter above. inside the initial spinner's onItemSelected method.

        /*5*///weekdays setOnItemSelectedListener
        weekdays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*5*/Toast.makeText(getBaseContext(), " Selected Day  " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                //getting the selected item from the weekdays spinner
                /*5*/selectedDay = weekdays.getSelectedItem().toString();

                /*2*///Start
                ParseQueryAdapter.QueryFactory<ParseObject> factory = new ParseQueryAdapter.QueryFactory<ParseObject>() {
                    public ParseQuery create() {
                        ParseQuery query = new ParseQuery("Faculty_free_timing");
                        query.whereEqualTo("faculty_intl", tmpInitialForFreeTiming);//These two queries...
                        query.whereEqualTo("faculty_workingDay", selectedDay);//...are working as AND Operators
                        return query;
                    }
                };//query adapter for weekdays Ends
                // Pass the factory into the ParseQueryAdapter's constructor.
                timingAdapter = new ParseQueryAdapter<ParseObject> (student_to_faculty.this, factory);
                timingAdapter.setTextKey("faculty_freeTiming");
                //setting the adapter
                timing.setAdapter(timingAdapter);
                timing.setSelection(1);
                /*2 End*/
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }/*5 OnItemSelected method ENDS*/);/*weekdays setOnItemSelectedListener parameter 5 ENDS*/

        /*2################################################################################################################*/

        /*2*///timing setOnItemSelectedListener
        timing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ParseObject theSelectedTime = timingAdapter.getItem(position);
                selectedTiming = theSelectedTime.getString("faculty_freeTiming");
                Toast.makeText(getBaseContext(), " Available timing  " + selectedTiming +" selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }/*2 OnItemSelected method ENDS*/);/*timing setOnItemSelectedListener parameter 2 ENDS*/

    }//onCreate ENDS

    //Student send requests to faculty by clicking this button
    public void sendFacultyARequest(View view) {//onClick
        studentRequestStrings = "Requested for course : "+selectedCourse+"."+" Wanted to come on : "+selectedDay+" at : "+selectedTiming+".";

        if (studentRequestStrings.isEmpty()) {
            Toast.makeText(student_to_faculty.this,"Please Select Above All",Toast.LENGTH_LONG).show();
        }
        else {
            //Saving into studentReq in Parse
            ParseObject studentReq = new ParseObject("Student_Request_to_Faculty");
            studentReq.put("student_ID",userName);
            studentReq.put("requested_to_Faculty",selectedInitial);
            studentReq.put("student_Request_Massage",studentRequestStrings);
            studentReq.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null){
                        Toast.makeText(student_to_faculty.this,"Successfully Saved Student Request",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(student_to_faculty.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });//saveInBackground
        }//else

    }//onClick sendFacultyARequest ENDS

    //Students able to see faculty room status by clicking this button
    public void SeeFacultyRoomStatus(View view){//onClick
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Faculty_Room_Status");
        query.whereEqualTo("faculty_initial", selectedInitial);
        query.orderByDescending("createdAt");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Toast.makeText(student_to_faculty.this,"Object Retriving Failed",Toast.LENGTH_LONG).show();
                } else {
                    fcltyRmSts.setText(object.getString("Room_Status"));
                }
            }
        });

    }//onClick SeeFacultyRoomStatus

}
