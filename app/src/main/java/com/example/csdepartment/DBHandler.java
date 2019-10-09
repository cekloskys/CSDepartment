package com.example.csdepartment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // initialize constants for DB version and name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "csdepartment.db";

    // initialize constants for student table
    public static final String TABLE_STUDENT = "student";
    public static final String COLUMN_STUDENT_ID = "_id";
    public static final String COLUMN_STUDENT_NAME = "name";
    public static final String COLUMN_STUDENT_YEAR = "year";
    public static final String COLUMN_STUDENT_MAJOR = "major";

    /**
     * Initializes a DBHandler.  Creates version of the database.
     * @param context reference to activity that initializes the DBHandler
     * @param factory null
     */
    public DBHandler (Context context, SQLiteDatabase.CursorFactory factory){
        // call superclass constructor
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Creates database table
     * @param sqLiteDatabase reference to csdepartment database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // define create statement for student table
        String query = "CREATE TABLE " + TABLE_STUDENT + "(" +
                COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STUDENT_NAME + " TEXT, " +
                COLUMN_STUDENT_YEAR + " TEXT, " +
                COLUMN_STUDENT_MAJOR + " TEXT" +
                ");";

        // execute create statement
        sqLiteDatabase.execSQL(query);
    }

    /**
     * This method gets executed when a new version of the database is initialized.
     * @param sqLiteDatabase reference to csdepartment database
     * @param oldVersion old version of database
     * @param newVersion new version of database
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // execute drop statement that drops student table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        // call method that creates table
        onCreate(sqLiteDatabase);
    }

    public void addStudent (String name, String year, String major) {

        // get reference to csdepartment database.
        SQLiteDatabase db = getWritableDatabase();

        // initialize an empty ContentValues
        ContentValues values = new ContentValues();

        // put key-value pairs in ContentValues.  The key must be the name
        // of a column and the value is the value to be inserted in the column.
        values.put(COLUMN_STUDENT_NAME, name);
        values.put(COLUMN_STUDENT_YEAR, year);
        values.put(COLUMN_STUDENT_MAJOR, major);

        // insert values into student table
        db.insert(TABLE_STUDENT, null, values);

        // close reference to shopper database
        db.close();
    }
}