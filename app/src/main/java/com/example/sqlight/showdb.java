package com.example.sqlight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.sqlight.Grades.TABLE_GRADES;
import static com.example.sqlight.Student.TABLE_STUDENT;

public class showdb extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    ListView lv;
    TextView show_tv;
    Spinner spinner;
    ArrayList<String> data_stud = new ArrayList<>();
    ArrayList<Integer>keys=new ArrayList<>();
    ArrayList<String>classL=new ArrayList<>();
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdb);
        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        String[] columns = {Student.NAME,Student.KEY_ID};
        crsr = db.query(TABLE_STUDENT, columns, Student.IS_ACTIVE+"=?",new String[]{"1"} , null, null, null);
        int col1 = crsr.getColumnIndex(Student.KEY_ID);
        int col2 = crsr.getColumnIndex(Student.NAME);
        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String name = crsr.getString(col2);
            data_stud.add(name);
            keys.add(key);
            crsr.moveToNext();
        }
        crsr.close();
        crsr=db.query(TABLE_GRADES,new String[]{Grades.CLASS_NAME}, null, null, null, null, null);
        int col3g=crsr.getColumnIndex(Grades.CLASS_NAME);
        crsr.moveToFirst();
        while ((!crsr.isAfterLast())){
            String classN=crsr.getString(col3g);
            if(!classL.contains(classN)){
                classL.add(classN); // adds all uniqe.
            }
            crsr.moveToNext();
        }
        crsr.close();
        db.close();
        spinner=(Spinner)findViewById(R.id.spinner_byName);
        show_tv=(TextView)findViewById(R.id.show_tv);
        spinner.setOnItemSelectedListener(this);
        show_tv.setText("");
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
        else if(s.equals("show data base")){
            si=new Intent(this,filterdShowdb.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        show_tv.setText("");
        if(s.equals("class")){
            String tmp=classL.get(position);
            db=hlp.getWritableDatabase();
            crsr=db.query(TABLE_GRADES,null,Grades.CLASS_NAME+"=?",new String[]{tmp},null,null,null,null);
            int col1g = crsr.getColumnIndex(Grades.STUDENT_ID);
            int col2g= crsr.getColumnIndex(Grades.GRADE);
            int col4g=crsr.getColumnIndex(Grades.QUARTER_NUMBER);
            crsr.moveToFirst();
            while(!crsr.isAfterLast()){
                int key=crsr.getInt(col1g);
                int index =keys.indexOf(key);
                String n=data_stud.get(index);
                Integer grade=crsr.getInt(col2g);
                String quarter=crsr.getString(col4g);
                String info=n+","+"quarter"+":"+quarter+","+grade.toString();// name quarter num and grade(class name is known)
                show_tv.setText(show_tv.getText().toString()+"\n"+info);
                crsr.moveToNext();
            }
            crsr.close();
            db.close();
        }

        else if(s.equals("name")){
            db=hlp.getWritableDatabase();
            Integer a=keys.get(position); //the student id.
            String sa=a.toString();
            String[]arg=new String[]{sa};
            crsr=db.query(TABLE_GRADES,null,Grades.STUDENT_ID+"=?",arg,null,null,null,null);
            int col2g= crsr.getColumnIndex(Grades.GRADE);
            int col4g=crsr.getColumnIndex(Grades.QUARTER_NUMBER);
            int col5g=crsr.getColumnIndex(Grades.CLASS_NAME);
            crsr.moveToFirst();
            while(!crsr.isAfterLast()) {
                Integer grade = crsr.getInt(col2g);
                String quarter = crsr.getString(col4g);
                String classn=crsr.getString(col5g);
                String info = classn+","+"quarter"+":"+quarter+","+grade.toString();// class name quarter num and grade(name is known)
                show_tv.setText(show_tv.getText().toString()+"\n"+info);
                crsr.moveToNext();
            }
            crsr.close();
            db.close();
        }
        else if(s.equals("grade")){
            db=hlp.getWritableDatabase();
            String tmp=classL.get(position);
            crsr=db.query(TABLE_GRADES,null,Grades.CLASS_NAME+"=?",new String[]{tmp},null,null,Grades.GRADE,null);
            int col1g = crsr.getColumnIndex(Grades.STUDENT_ID);
            int col2g= crsr.getColumnIndex(Grades.GRADE);
            int col4g=crsr.getColumnIndex(Grades.QUARTER_NUMBER);
            int col5g=crsr.getColumnIndex(Grades.CLASS_NAME);
            crsr.moveToFirst();
            while(!crsr.isAfterLast()) {
                int key = crsr.getInt(col1g);
                Integer grade = crsr.getInt(col2g);
                String quarter = crsr.getString(col4g);
                String classn=crsr.getString(col5g);
                int index =keys.indexOf(key);
                String n=data_stud.get(index);
                String info=n+","+"quarter"+":"+quarter+","+grade.toString();// name quarter num and grade(class name is known)
                show_tv.setText(show_tv.getText().toString()+"\n"+info);
                crsr.moveToNext();
            }
            crsr.close();
            db.close();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void sort(View view) {
        ArrayAdapter<String>adp=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,classL);// spinner adapter for high to low class sort.
        spinner.setAdapter(adp);
        s="grade";// defult.
    }

    public void name(View view) {
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, data_stud);// spinner adapter for name sort.
        spinner.setAdapter(adp);
        s="name";
    }
    public void byClass(View view) {
        ArrayAdapter<String>adp=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,classL);// spinner adapter for class sort.
        spinner.setAdapter(adp);
        s="class";
     }
    }