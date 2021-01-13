package com.example.sqlight;
/**
 * @author yoad wolfson.
 * @version 1.5
 * SQLite data base exercise.
 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity  {
    SQLiteDatabase db;//the SQLite data base.
    HelperDB hlp;// the class who builds the data base.
    ContentValues cv=new ContentValues();
    EditText motherNE,fatherNE,homeNE,nameE,fatherE,numberE,motherE;
    String name,mother,father,mother_number,father_number,home_number,phone_number;// all the data in strings.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
        fatherNE=(EditText)findViewById(R.id.fatherN);
        motherNE=(EditText)findViewById(R.id.motherN);
        homeNE=(EditText)findViewById(R.id.homen);
        nameE=(EditText)findViewById(R.id.name);
        fatherE=(EditText)findViewById(R.id.father);
        numberE=(EditText)findViewById(R.id.phone);
        motherE=(EditText)findViewById(R.id.mother);
    }
    /**
     * creates the xml general option menu.
     * <p>
     * @param menu the xml general menu.
     * @return true if the menu was created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * when item go clicked in the option menu goes to the activity that was chosen.
     * <p>
     * @param item the item in the menu that got clicked.
     * @return true if was operated successfully.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent si;
        String s=item.getTitle().toString();
        if(s.equals("credits screen")) {
            si = new Intent(this,credits.class);
            startActivity(si);
        }
        else if (s.equals("show data base")){
            si=new Intent(this,showdb.class);
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

    public void commit(View view) {
        name=nameE.getText().toString();
        phone_number=numberE.getText().toString();
        mother_number=motherNE.getText().toString();
        father=fatherE.getText().toString();
        mother=motherE.getText().toString();
        home_number=homeNE.getText().toString();
        father_number=fatherNE.getText().toString();
        if(name.equals(null)){
            Toast.makeText(MainActivity.this, "enter a name", Toast.LENGTH_SHORT).show();
        }
        if(mother_number.equals(null)&&father_number.equals(null)&&phone_number.equals(null)){
            Toast.makeText(MainActivity.this, "enter a communication way", Toast.LENGTH_SHORT).show();
        }//name and a number is enough for adding student
        cv.put(Student.NAME,name);
        cv.put(Student.PHONE_NUMBER, phone_number);
        cv.put(Student.MOTHER_NUMBER, mother_number);
        cv.put(Student.FATHER, father);
        cv.put(Student.MOTHER, mother);
        cv.put(Student.HOME_NUMBER, home_number);
        cv.put(Student.FATHER_NUMBER, father_number);// first column student committed into db.
        db = hlp.getWritableDatabase();
        db.insert(Student.TABLE_STUDENT,null,cv);
        db.close();
        nameE.setText("");
        numberE.setText("");
        motherNE.setText("");
        fatherE.setText("");
        motherE.setText("");
        homeNE.setText("");
        fatherNE.setText("");// clear the text views.
    }
}