package com.example.sanchoy.bracualleviator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class faculty_to_student extends ActionBarActivity {
    String objIdOfStudentRequest;
    String StudentFromParse;
    String StudentMassageFromParse;
    String tmpStdId;
    String requestApproverInitial;
    String approveButton = "";
    String facultyMassage = "";
    //EditText
    /*1*/EditText needyStudentId;
    /*2*/EditText studentRequestDetail;
    /*3*/EditText facultyMassageBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_to_student);

        /*1*/needyStudentId = (EditText)findViewById(R.id.needHelpStudentID);
        /*2*/studentRequestDetail = (EditText)findViewById(R.id.stdRqDetail);
        /*3*/facultyMassageBox = (EditText)findViewById(R.id.facultyMsgBox);

        //Intent to get Object from faculty page ListView
        Intent intent = getIntent();
        objIdOfStudentRequest = intent.getStringExtra("objectID");

        ParseUser currentUser = ParseUser.getCurrentUser();//getting the current faculty which is a parse user object
        //getting current faculty initial
        requestApproverInitial = currentUser.getUsername();

        //Query for showing student massage detail
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student_Request_to_Faculty");
        query.getInBackground(objIdOfStudentRequest, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, com.parse.ParseException e) {
                if (e == null) {
                    tmpStdId = StudentFromParse = parseObject.getString("student_ID");
                    /*1*/needyStudentId.setText(StudentFromParse);//setting id into above EditTexts
                    tmpStdId = StudentFromParse;
                    StudentMassageFromParse = parseObject.getString("student_Request_Massage");
                    /*2*/studentRequestDetail.setText(StudentMassageFromParse);//setting request middle EditTexts
                } else {
                    Toast.makeText(faculty_to_student.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        //Query for showing student massage detail ENDS

    }

    //Approve onClick
    public void sendApproved(View v){
        approveButton = "approved";
        facultyMassage = facultyMassageBox.getText().toString().trim();
        createParseQuery();
    }

    //Busy onClick
    public void sendBusy(View v){
        approveButton = "busy";
        facultyMassage = facultyMassageBox.getText().toString().trim();
        createParseQuery();
    }

    //Reject onClick
    public void sendRejected(View v){
        approveButton = "rejected";
        facultyMassage = facultyMassageBox.getText().toString().trim();
        createParseQuery();
    }

    //Send Massage to student onClick
    public void sendDefaultMassageToStudent(View v){
        facultyMassage = facultyMassageBox.getText().toString().trim();
        createParseQuery();
    }

    public void createParseQuery(){
        //Saving into Faculty_approval in Parse
        ParseObject approversJob = new ParseObject("Faculty_approval");
        approversJob.put("request_senders_id",tmpStdId);
        approversJob.put("approvers_initial",requestApproverInitial);
        approversJob.put("approval_button",approveButton);
        approversJob.put("faculty_massage_to_student",facultyMassage);
        approversJob.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Toast.makeText(faculty_to_student.this,"Successfully Saved Approvers Query",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(faculty_to_student.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });//saveInBackground

    }
}
