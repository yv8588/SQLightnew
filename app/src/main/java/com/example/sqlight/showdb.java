package com.example.sqlight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import static com.example.sqlight.Student.TABLE_STUDENT;

public class showdb extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    ArrayList<String> data_stud = new ArrayList<>();
    ArrayList<String> data_grade = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdb);
        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        crsr = db.query(TABLE_STUDENT, null, null, null, null, null, null);
        int col1 = crsr.getColumnIndex(Student.KEY_ID);
        int col2 = crsr.getColumnIndex(Student.NAME);
        int col3 = crsr.getColumnIndex(Student.MOTHER);
        int col4 = crsr.getColumnIndex(Student.FATHER);
        int col5 = crsr.getColumnIndex(Student.MOTHER_NUMBER);
        int col6 = crsr.getColumnIndex(Student.FATHER_NUMBER);
        int col7 = crsr.getColumnIndex(Student.HOME_NUMBER);
        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String name = crsr.getString(col2);
            String mother = crsr.getString(col3);
            String father = crsr.getString(col4);
            String mother_number = crsr.getString(col5);
            String father_number = crsr.getString(col6);
            String home_number = crsr.getString(col7);
            String tmp = "" + key + ", " + name + ", " + mother + ", " + father+","+mother_number+","+father_number+","+home_number;
            data_stud.add(tmp);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();
    }
    /**
     * creates the xml general option menu
     * <p>
     * @param menu the xml general menu
     * @return true if the menu was created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * when item go clicked in the option menu goes to the activity that was chosen
     * <p>
     * @param item the item in the menu that got clicked
     * @return true if was operated successfully
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent si;
        String s=item.getTitle().toString();
        if(s.equals("credits screen")) {
            si = new Intent(this,credits.class);
            startActivity(si);
        }
        else if (s.equals("main activity")){
            si=new Intent(this,MainActivity.class);
            startActivity(si);
        }
        else if(s.equals("grades")){
            si=new Intent(this,grade.class);
            startActivity(si);
        }
        else{
            si=new Intent(this,filterdShowdb.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}