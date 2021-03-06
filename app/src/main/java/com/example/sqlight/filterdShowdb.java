package com.example.sqlight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.sqlight.Grades.TABLE_GRADES;
import static com.example.sqlight.Student.KEY_ID;
import static com.example.sqlight.Student.TABLE_STUDENT;

public class filterdShowdb extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinames;
    EditText name_et,num_et,home_number_et,father_et,father_number_et,mother_et,mother_number_et;
    ArrayList<String>namesL=new ArrayList<>();
    ArrayList<String>fatherL=new ArrayList<>();
    ArrayList<String>motherL=new ArrayList<>();
    ArrayList<String>fatherNL=new ArrayList<>();
    ArrayList<String>motherNL=new ArrayList<>();
    ArrayList<String>homeNL=new ArrayList<>();
    ArrayList<String>phoneNL=new ArrayList<>();
    ArrayList<Integer>keys=new ArrayList<>();
    ArrayAdapter<String> adp;
    int pos;
    HelperDB hlp;
    SQLiteDatabase db;
    Cursor crsr;
    ContentValues cv=new ContentValues();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterd_showdb);
        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        db.close();
        spinames=(Spinner)findViewById(R.id.spinames);
        name_et=(EditText) findViewById(R.id.name_et);
        num_et=(EditText) findViewById(R.id.num_et);
        home_number_et=(EditText) findViewById(R.id.home_number_et);
        father_et=(EditText) findViewById(R.id.father_et);
        mother_et=(EditText)findViewById(R.id.mother_et);
        father_number_et=(EditText) findViewById(R.id.father_number_et);
        mother_number_et=(EditText)findViewById(R.id.mother_number_et);
        db=hlp.getWritableDatabase();
        crsr = db.query(TABLE_STUDENT, null, null, null, null, null, null, null);
        int col9=crsr.getColumnIndex(Student.KEY_ID);
        int col2 = crsr.getColumnIndex(Student.NAME);
        int col3 = crsr.getColumnIndex(Student.PHONE_NUMBER);
        int col4 = crsr.getColumnIndex(Student.HOME_NUMBER);
        int col5 = crsr.getColumnIndex(Student.FATHER);
        int col6 = crsr.getColumnIndex(Student.MOTHER);
        int col7 = crsr.getColumnIndex(Student.FATHER_NUMBER);
        int col8 = crsr.getColumnIndex(Student.MOTHER_NUMBER);
        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
                String name = crsr.getString(col2);
                String phoneN = crsr.getString(col3);
                String homeN = crsr.getString(col4);
                String father = crsr.getString(col5);
                String mother = crsr.getString(col6);
                String fatherN = crsr.getString(col7);
                String motherN = crsr.getString(col8);
                Integer key=crsr.getInt(col9);
                keys.add(key);
                fatherL.add(father);
                fatherNL.add(fatherN);
                motherL.add(mother);
                motherNL.add(motherN);
                homeNL.add(homeN);
                phoneNL.add(phoneN);
                namesL.add(name);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();

        adp=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,namesL);// spinner adapter
        spinames.setAdapter(adp);
        spinames.setOnItemSelectedListener(this);

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
        else if(s.equals("filterd data base" )){
            si=new Intent(this,showdb.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * when item selected in the spinner(a name) show the person info.
     * <p>
     * @param parent the spinner.
     * @param view the row in the spinner.
     * @param position the place in the Array list.
     * @param id the place in the spinner.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos=position;
        name_et.setText(namesL.get(position));
        num_et.setText(phoneNL.get(position));
        home_number_et.setText(homeNL.get(position));
        father_et.setText(fatherL.get(position));
        mother_et.setText(motherL.get(position));
        father_number_et.setText(fatherNL.get(position));
        mother_number_et.setText(motherNL.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    /**
     * updates data base with provided info.
     * <p>
     * @param view the button that got clicked.
     */
    public void update(View view) {
        String name=name_et.getText().toString();
        String father=father_et.getText().toString();
        String mother=mother_et.getText().toString();
        String motherN=mother_number_et.getText().toString();
        String fatherN=father_number_et.getText().toString();
        String homeN=home_number_et.getText().toString();
        String number=num_et.getText().toString();
        db=hlp.getWritableDatabase();
        if(name!=null&&!name.equals(namesL.get(pos))){
            db=hlp.getWritableDatabase();
            cv.put(Student.NAME,name);
            db.update(TABLE_STUDENT,cv,Student.NAME+"=?",new String[]{namesL.get(pos)});
            namesL.set(pos,name);
            adp.notifyDataSetChanged();
            db.close();
        }
        else if(father!=null&&!father.equals(fatherL.get(pos))){
            db=hlp.getWritableDatabase();
            cv.put(Student.FATHER,father);
            db.update(TABLE_STUDENT,cv,Student.FATHER+"=?",new String[]{fatherL.get(pos)});
            fatherL.set(pos,father);
            db.close();
        }
        else if(mother!=null&&!mother.equals(motherL.get(pos))){
            db=hlp.getWritableDatabase();
            cv.put(Student.MOTHER,mother);
            db.update(TABLE_STUDENT,cv,Student.MOTHER+"=?",new String[]{motherL.get(pos)});
            motherL.set(pos,mother);
            db.close();
        }
        else if(motherN!=null&&!motherN.equals(motherNL.get(pos))){
            db=hlp.getWritableDatabase();
            cv.put(Student.MOTHER_NUMBER,motherN);
            db.update(TABLE_STUDENT,cv,Student.MOTHER_NUMBER+"=?",new String[]{motherNL.get(pos)});
            motherNL.set(pos,motherN);
            db.close();
        }
        else if(fatherN!=null&&!fatherN.equals(fatherNL.get(pos))) {
            db=hlp.getWritableDatabase();
            cv.put(Student.FATHER_NUMBER, fatherN);
            db.update(TABLE_STUDENT, cv, Student.FATHER_NUMBER+"=?", new String[]{fatherNL.get(pos)});
            fatherNL.set(pos, fatherN);
            db.close();
        }
        else if(homeN!=null&&!homeN.equals(homeNL.get(pos))) {
            db=hlp.getWritableDatabase();
            cv.put(Student.HOME_NUMBER, homeN);
            db.update(TABLE_STUDENT, cv, Student.HOME_NUMBER+"=?", new String[]{homeNL.get(pos)});
            homeNL.set(pos, homeN);
            db.close();
        }
        else if(number!=null&&!homeN.equals(homeNL.get(pos))){
            db=hlp.getWritableDatabase();
          cv.put(Student.PHONE_NUMBER, number);
          db.update(TABLE_STUDENT, cv, Student.PHONE_NUMBER+"=?", new String[]{phoneNL.get(pos)});
          phoneNL.set(pos,number);
          db.close();
        }
    }
    /**
     * deletes student from data base.
     * <p>
     * @param view the button that got clicked.
     */
    public void del(View view) {
        db = hlp.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID+"=?", new String[]{Integer.toString(pos+1)});
        db.delete(TABLE_GRADES,Grades.STUDENT_ID+"=?",new String[]{Integer.toString(pos+1)});
        db.close();
        Toast.makeText(this,"Student Deleted",Toast.LENGTH_SHORT).show();
        if(pos==0){
            name_et.setText(namesL.get(pos+1));
            num_et.setText(phoneNL.get(pos+1));
            home_number_et.setText(homeNL.get(pos+1));
            father_et.setText(fatherL.get(pos+1));
            mother_et.setText(motherL.get(pos+1));
            father_number_et.setText(fatherNL.get(pos+1));
            mother_number_et.setText(motherL.get(pos+1));
        }
        else {
            name_et.setText(namesL.get(pos-1));
            num_et.setText(phoneNL.get(pos-1));
            home_number_et.setText(homeNL.get(pos-1));
            father_et.setText(fatherL.get(pos-1));
            mother_et.setText(motherL.get(pos-1));
            father_number_et.setText(fatherNL.get(pos-1));
            mother_number_et.setText(motherL.get(pos-1));
        }
        namesL.remove(pos);
        fatherL.remove(pos);
        fatherNL.remove(pos);
        motherL.remove(pos);
        motherNL.remove(pos);
        homeNL.remove(pos);
        phoneNL.remove(pos);
        keys.remove(pos);
        adp.notifyDataSetChanged();// updates the adapter with out the deleted name.

    }
}