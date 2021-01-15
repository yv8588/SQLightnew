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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.sqlight.Student.TABLE_STUDENT;

public class filterdShowdb extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
HelperDB hlp;
SQLiteDatabase db;
Cursor crsr;
Spinner spinames;
TextView nametv,numtv,home_numbertv,fathertv,father_numbertv,mothertv,mother_numbertv;
ArrayList<String>namesL=new ArrayList<>();
ArrayList<String>fatherL=new ArrayList<>();
ArrayList<String>motherL=new ArrayList<>();
ArrayList<String>fatherNL=new ArrayList<>();
ArrayList<String>motherNL=new ArrayList<>();
ArrayList<String>homeNL=new ArrayList<>();
ArrayList<String>phoneNL=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterd_showdb);
        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        spinames=(Spinner)findViewById(R.id.spinames);
        nametv=(TextView) findViewById(R.id.nametv);
        numtv=(TextView) findViewById(R.id.numtv);
        home_numbertv=(TextView) findViewById(R.id.home_numbertv);
        fathertv=(TextView) findViewById(R.id.fathertv);
        mothertv=(TextView)findViewById(R.id.mothertv);
        father_numbertv=(TextView) findViewById(R.id.father_numbertv);
        mother_numbertv=(TextView)findViewById(R.id.mother_numbertv);
        crsr = db.query(TABLE_STUDENT, null, null, null, null, null, null, null);
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
            String homeN =crsr.getString(col4);
            String father=crsr.getString(col5);
            String mother=crsr.getString(col6);
            String fatherN=crsr.getString(col7);
            String motherN=crsr.getString(col8);
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
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,namesL);// spinner adapter
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
        else{
            si=new Intent(this,showdb.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nametv.setText("students name"+":"+namesL.get(position));
        numtv.setText("students phone"+":"+phoneNL.get(position));
        home_numbertv.setText("students home number"+":"+homeNL.get(position));
        fathertv.setText("students father"+":"+fatherL.get(position));
        mothertv.setText("students mother"+":"+motherL.get(position));
        father_numbertv.setText("fathers number"+":"+fatherNL.get(position));
        mother_numbertv.setText("mothers number"+":"+motherNL.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}