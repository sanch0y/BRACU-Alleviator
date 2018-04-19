package com.example.sanchoy.bracualleviator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class faculty_page extends ActionBarActivity {
    String initial;
    String name;
    String department;
    String on = "Inside the room";
    String off= "Outside of room";
    //EditText
    EditText etName;
    EditText etInitial;
    EditText etDepartment;
    //Switch
    Switch aSwitch;
    //ListView
    ListView lv;
    //List
    List<ParseObject> lpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_page);

        etName = (EditText)findViewById(R.id.FacultyName);
        etInitial = (EditText)findViewById(R.id.facultyInitial);
        etDepartment = (EditText)findViewById(R.id.FacultyDepartment);
        aSwitch = (Switch)findViewById(R.id.roomStatus);
        lv = (ListView)findViewById(R.id.studentRequestsWithID);

        /*For above*/
        ParseUser currentUser = ParseUser.getCurrentUser();//getting the current faculty which is a parse user object
        //getting current faculty initial
        initial = currentUser.getUsername();
        etInitial.setText(initial);//setting faculty initial at the top
        //getting current faculty name
        name = currentUser.getString("faculty_name");
        etName.setText(name);//setting faculty name at the top//department
        //getting current faculty department
        department = currentUser.getString("faculty_department");
        etDepartment.setText(department);//setting faculty department at the top
        /*for above*/

        /*for toggling*/
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //[*PARSE]
                ParseObject facultyRoomStatus = new ParseObject("Faculty_Room_Status");
                facultyRoomStatus.put("faculty_initial",initial);
                //[PARSE*]
                //checking toggle
                if (isChecked) {
                    facultyRoomStatus.put("Room_Status",on);//[PARSE]
                    Toast.makeText(faculty_page.this,on,Toast.LENGTH_LONG).show();
                } else {
                    facultyRoomStatus.put("Room_Status",off);//[PARSE]
                    Toast.makeText(faculty_page.this,off,Toast.LENGTH_LONG).show();
                }
                //saveInBackgroung[*PARSE]
                facultyRoomStatus.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        if (e == null){
                            Toast.makeText(faculty_page.this,"Successfully Saved Room Status",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(faculty_page.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }//done ENDS
                }/*saveCallback*/);//saveInBackground ENDS[PARSE*]
            }//onCheckedChanged ENDS
        }/*setOnCheckedChangeListener ENDS*/);//SetOnCheckedChangeListener ENDS
        /*for toggling ENDS*/

        /*for ListView*/
        if(currentUser != null){
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Student_Request_to_Faculty");
            query.whereEqualTo("requested_to_Faculty",initial);
            query.orderByDescending("createdAt");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> requestListObjects, ParseException e) {
                    if (e == null) {
                        lpo = requestListObjects;
                        requestListviewCustomAdapter rqlvca = new requestListviewCustomAdapter(faculty_page.this, lpo);
                        lv.setAdapter(rqlvca);
                    } else {
                        System.err.println("Error in fetching requests from parse");
                    }
                }
            });
        }
        else {Toast.makeText(faculty_page.this,"CURRENT USER IS NULL",Toast.LENGTH_LONG).show();
        }
        /*for ListView ENDS*/

        /*for setOnItemClickListener*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject rqstObj = lpo.get(position);
                String objectID = rqstObj.getObjectId();
                Intent goToRespondStudentRequests = new Intent("com.example.sanchoy.bracualleviator.faculty_to_student");
                goToRespondStudentRequests.putExtra("objectID",objectID);
                startActivity(goToRespondStudentRequests);
            }
        });
        /*for setOnItemClickListener ENDS*/

    }//onCreate ENDS


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_faculty_page, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_faculty_logout) {
            Intent i = new Intent("com.example.sanchoy.bracualleviator.facultyLogin_activity");
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}
