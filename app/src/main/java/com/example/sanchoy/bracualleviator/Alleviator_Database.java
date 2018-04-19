package com.example.sanchoy.bracualleviator;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Alleviator_Database extends SQLiteOpenHelper{
    //context
    private Context c;
    //database name and version
    private static final String DATABASE_NAME = "AlleviatorDatabase";
    private static final int DATABASE_VERSION = 6;

    //(1)Table Name student//
    private static final String TABLE_NAME_STUDENT = "student";
    //columns
    private static final String COLUMN_ID = "_id";//PRIMARY_KEY
    private static final String COLUMN_STUDENT_PASSWORD = "s_password";
    private static final String COLUMN_VERIFICATION = "verification";
    private static final String COLUMN_IMAGE = "idcard_image";
    //Create table query
    private static final String CREATE_TABLE_STUDENT =
            "CREATE TABLE " + TABLE_NAME_STUDENT + " (" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_STUDENT_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_VERIFICATION + " TEXT NOT NULL, " +
                    COLUMN_IMAGE + " TEXT NOT NULL);" ;

    //(2)Table name faculty//
    private static final String TABLE_NAME_FACULTY = "faculty";
    //columns
    private static final String COLUMN_INITIAL = "_initial";//PRIMARY_KEY
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DEPARTMENT = "department";
    private static final String COLUMN_FACULTY_PASSWORD = "f_password";
    private static final String COLUMN_EMAIL = "bracu_email";
    private static final String COLUMN_STATUS = "room_status";
    //Create table query
    private static final String CREATE_TABLE_FACULTY =
            "CREATE TABLE " + TABLE_NAME_FACULTY + " (" +
                    COLUMN_INITIAL + " TEXT PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_DEPARTMENT + " TEXT NOT NULL, " +
                    COLUMN_FACULTY_PASSWORD + " TEXT NOT NULL, "+
                    COLUMN_EMAIL+ " TEXT NOT NULL, "+
                    COLUMN_STATUS+ " TEXT NOT NULL);" ;

    //(3)Table name faculty_schedule//
    private static final String TABLE_NAME_FACULTY_SCHEDULE = "schedule";
    //columns
    private static final String COLUMN_ROW_NO = "_row_number";//PRIMARY_KEY
    private static final String COLUMN_FACULTY_I = "faculty_i";
    private static final String COLUMN_FACULTY_WORKING_DAY = "faculty_working_day";
    private static final String COLUMN_FACULTY_FREE_TIMING = "faculty_free_timing";
    //Create table query
    private static final String CREATE_TABLE_FACULTY_SCHEDULE =
            "CREATE TABLE " + TABLE_NAME_FACULTY_SCHEDULE + " (" +
                    COLUMN_ROW_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FACULTY_I + " TEXT NOT NULL, " +
                    COLUMN_FACULTY_WORKING_DAY + " TEXT NOT NULL, "+
                    COLUMN_FACULTY_FREE_TIMING+ " TEXT NOT NULL);" ;

    //(4)Table name request//
    private static final String TABLE_NAME_REQUEST = "request";
    //columns
    private static final String COLUMN_FACULTY_INITIAL = "_faculty_initial";//PRIMARY_KEY
    private static final String COLUMN_STUDENT_ID = "_student_id";//PRIMARY_KEY
    private static final String COLUMN_FACULTY_MASSAGE = "faculty_massage";
    private static final String COLUMN_STUDENT_REQUEST = "student_request";
    //Create table query
    private static final String CREATE_TABLE_REQUEST =
            "CREATE TABLE " + TABLE_NAME_REQUEST + " (" +
                    COLUMN_FACULTY_INITIAL + " TEXT NOT NULL, " +
                    COLUMN_STUDENT_ID + " TEXT NOT NULL, " +
                    COLUMN_FACULTY_MASSAGE + " TEXT NOT NULL, " +
                    COLUMN_STUDENT_REQUEST + " TEXT NOT NULL, " +
                    "PRIMARY KEY " +"("+COLUMN_FACULTY_INITIAL+","+ COLUMN_STUDENT_ID+"));" ;

    //(5)Table name bracu//
    private static final String TABLE_NAME_BRACU = "bracu";
    //columns
    private static final String COLUMN_BULDING_NUMBER = "_building";//PRIMARY_KEY/*ask teacher*/
    private static final String COLUMN_LAB_ROOM = "lab_room_number";
    private static final String COLUMN_LAB_WEEKDAYS = "lab_day";
    private static final String COLUMN_LAB_TIMING = "lab_timing";
    //Create table query
    private static final String CREATE_TABLE_BRACU =
            "CREATE TABLE " + TABLE_NAME_BRACU + " (" +
                    COLUMN_BULDING_NUMBER + " TEXT PRIMARY KEY, " +
                    COLUMN_LAB_ROOM + " TEXT NOT NULL, " +
                    COLUMN_LAB_WEEKDAYS + " TEXT NOT NULL, " +
                    COLUMN_LAB_TIMING+ " TEXT NOT NULL);" ;

    //(6)Table name courses_of_faculty//
    private static final String TABLE_NAME_FACULTY_COURSES = "courses_of_faculty";
    //columns
    private static final String COLUMN_ROW_PRIMARY_KEY = "_pk_row_number";//PRIMARY_KEY
    private static final String COLUMN_FAC_IN = "fac_in";
    private static final String COLUMN_FAC_TAKEN_COURSES = "taken_courses";
    //Create table query
    private static final String CREATE_TABLE_FACULTY_COURSES =
            "CREATE TABLE " + TABLE_NAME_FACULTY_COURSES + " (" +
                    COLUMN_ROW_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FAC_IN + " TEXT NOT NULL, " +
                    COLUMN_FAC_TAKEN_COURSES+ " TEXT NOT NULL);" ;

    //Drop table query//
    //1
    private static final String DROP_TABLE_STUDENT = "DROP TABLE IF EXISTS " + TABLE_NAME_STUDENT;
    //2
    private static final String DROP_TABLE_FACULTY = "DROP TABLE IF EXISTS " + TABLE_NAME_FACULTY;
    //3
    private static final String DROP_TABLE_FACULTY_SCHEDULE = "DROP TABLE IF EXISTS " + TABLE_NAME_FACULTY_SCHEDULE;
    //4
    private static final String DROP_TABLE_REQUEST = "DROP TABLE IF EXISTS " + TABLE_NAME_REQUEST;
    //5
    private static final String DROP_TABLE_BRACU = "DROP TABLE IF EXISTS " + TABLE_NAME_BRACU;
    //6
    private static final String DROP_TABLE_FACULTY_COURSES = "DROP TABLE IF EXISTS " + TABLE_NAME_FACULTY_COURSES;
    //Drop table query//

    //constructor//
    public Alleviator_Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //1
        try {
            db.execSQL(CREATE_TABLE_STUDENT);
        }catch (Exception e)
        {
            System.out.print("Error in 1");
        }

        //2
        try {
            db.execSQL(CREATE_TABLE_FACULTY);
        }catch (Exception e)
        {
            System.out.print("Error in 2");
        }

        //3
        try {
            db.execSQL(CREATE_TABLE_FACULTY_SCHEDULE);
        }catch (Exception e)
        {
            System.out.print("Error in 3");
        }

        //4
        try {
            db.execSQL(CREATE_TABLE_REQUEST);
        }catch (Exception e)
        {
            System.out.print("Error in 4");
        }

        //5
        try {
            db.execSQL(CREATE_TABLE_BRACU);
        }catch (Exception e)
        {
            System.out.print("Error in 5");
        }

        //6
        try {
            db.execSQL(CREATE_TABLE_FACULTY_COURSES);
        }catch (Exception e)
        {
            System.out.print("Error in 6");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //1
        try {
            db.execSQL(DROP_TABLE_STUDENT);
        }catch (Exception e)
        {
            System.out.print("Error in drop table 1");
        }

        //2
        try {
            db.execSQL(DROP_TABLE_FACULTY);
        }catch (Exception e)
        {
            System.out.print("Error in drop table 2");
        }

        //3
        try {
            db.execSQL(DROP_TABLE_FACULTY_SCHEDULE);
        }catch (Exception e)
        {
            System.out.print("Error in drop table 3");
        }

        //4
        try {
            db.execSQL(DROP_TABLE_REQUEST);
        }catch (Exception e)
        {
            System.out.print("Error in drop table 4");
        }

        //5
        try {
            db.execSQL(DROP_TABLE_BRACU);
        }catch (Exception e)
        {
            System.out.print("Error in drop table 5");
        }

        //6
        try {
            db.execSQL(DROP_TABLE_FACULTY_COURSES);
        }catch (Exception e)
        {
            System.out.print("Error in drop table 6");
        }
        //creating new tables
        onCreate(db);
    }
}
