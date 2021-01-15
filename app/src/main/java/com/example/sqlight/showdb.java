package com.example.sqlight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.sqlight.Grades.TABLE_GRADES;
import static com.example.sqlight.Student.TABLE_STUDENT;

public class showdb extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    ListView lv;
    ArrayList<String> data_stud = new ArrayList<>();
    ArrayList<Integer>key=new ArrayList<>();
    ArrayList<String> grade1 = new ArrayList<>();
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
            data_stud.add(name);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();
        String TABLE = TABLE_GRADES;
        String[] columns = {Grades.GRADE,Grades.STUDENT_ID,Grades.CLASS_NAME,Grades.QUARTER_NUMBER};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        crsr=db.query(TABLE,columns,selection,selectionArgs,groupBy,having,orderBy,limit);
        int col1g = crsr.getColumnIndex(Grades.STUDENT_ID);
        int col2g= crsr.getColumnIndex(Grades.GRADE);
        int col3g=crsr.getColumnIndex(Grades.CLASS_NAME);
        int col4g=crsr.getColumnIndex(Grades.QUARTER_NUMBER);
        crsr.moveToFirst();
        while ((!crsr.isAfterLast())){
            int id =crsr.getInt(col1g);
            String classN=crsr.getString(col3g);
            String grade=crsr.getString(col2g);
            String quart=crsr.getString(col4g);
            String tmp = "" + id + ", " + classN + ", " + grade+","+quart;
           grade1.add(tmp);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();
        CustomAdapter customadp = new CustomAdapter(getApplicationContext(),data_stud, grade1);
        lv.setAdapter(customadp);
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
        else if (s.equals("enter student")){
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