package com.example.sqlight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.sqlight.Grades.CLASS_NAME;
import static com.example.sqlight.Grades.GRADE;
import static com.example.sqlight.Grades.QUARTER_NUMBER;
import static com.example.sqlight.Grades.STUDENT_ID;
import static com.example.sqlight.Grades.TABLE_GRADES;
import static com.example.sqlight.Student.FATHER;
import static com.example.sqlight.Student.FATHER_NUMBER;
import static com.example.sqlight.Student.HOME_NUMBER;
import static com.example.sqlight.Student.KEY_ID;
import static com.example.sqlight.Student.MOTHER;
import static com.example.sqlight.Student.MOTHER_NUMBER;
import static com.example.sqlight.Student.NAME;
import static com.example.sqlight.Student.PHONE_NUMBER;
import static com.example.sqlight.Student.TABLE_STUDENT;

/**
 * a class that helps create the db.
 */
public class HelperDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "newDB.db";
    private static final int DATABASE_VERSION = 14;
    String strCreate, strDelete;
    public HelperDB(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * creates the data base.
     * <p>
     * @param db the data base.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        strCreate="CREATE TABLE "+TABLE_STUDENT;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+NAME+" TEXT,";
        strCreate+=" "+PHONE_NUMBER+" TEXT,";
        strCreate+=" "+HOME_NUMBER+" TEXT,";
        strCreate+=" "+FATHER+" TEXT,";
        strCreate+=" "+MOTHER+" TEXT,";
        strCreate+=" "+FATHER_NUMBER+" TEXT,";
        strCreate+=" "+MOTHER_NUMBER+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_GRADES;
        strCreate+="("+STUDENT_ID+" TEXT,";
        strCreate+=" "+CLASS_NAME+" TEXT,";
        strCreate+=" "+QUARTER_NUMBER+" TEXT,";
        strCreate+=" "+GRADE+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);
    }

    @Override
    /** makes a new data base with new data and older.
     * <p>
     * @param db the SQLite data base.
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        strDelete="DROP TABLE IF EXISTS "+TABLE_STUDENT;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_GRADES;
        db.execSQL(strDelete);
        onCreate(db);

    }
}
