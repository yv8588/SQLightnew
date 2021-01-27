package com.example.sqlight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.sqlight.Student.TABLE_STUDENT;

public class grade extends AppCompatActivity implements AdapterView.OnItemLongClickListener
        , AdapterView.OnItemSelectedListener{
    Spinner namespin;
    ListView listGrades;
    SQLiteDatabase db;//the SQLite data base.
    HelperDB hlp;// the class who builds the data base.
    ContentValues cv2=new ContentValues();
    Cursor crsr;
    AlertDialog.Builder adb;
    String[]GradesDATA={"CLASS NAME","QUARTER NUMBER","GRADE"};
    String grade,class_name,quarter;
    String student_ID;
    ArrayList<Integer> keys = new ArrayList<>();// students key id.
    ArrayList<String> names= new ArrayList<>();// students name.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        namespin=(Spinner)findViewById(R.id.namespin);
        listGrades = (ListView) findViewById(R.id.listGrade);

        listGrades.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listGrades.setOnItemLongClickListener(this);
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, GradesDATA);// the adapter for the list/
        listGrades.setAdapter(adp2);
        namespin.setOnItemSelectedListener(this);

        String TABLE = TABLE_STUDENT;
        String[] columns = {Student.KEY_ID, Student.NAME};
        db = hlp.getWritableDatabase();
        crsr = db.query(TABLE, columns, null,null, null, null, null, null);
        int col1 = crsr.getColumnIndex(Student.KEY_ID);
        int col2 = crsr.getColumnIndex(Student.NAME);
        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String name = crsr.getString(col2);
            keys.add(key);
            names.add(name);
            crsr.moveToNext();
        }// creates keys and names array list with same index key_id to name.
        crsr.close();
        db.close();

        ArrayAdapter<String>spin=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,names);// spinner adapter
        namespin.setAdapter(spin);
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
            si=new Intent(this,filterdShowdb.class);
            startActivity(si);
        }
        else if(s.equals("enter student")) {
           si=new Intent(this,MainActivity.class);
           startActivity(si);
        }
        else if(s.equals("filterd data base")){
            si=new Intent(this,showdb.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * enters a column into the db when clicked.
     * <p>
     * @param view the commit button.
     */
    public void commit(View view) {
        int q=Integer.valueOf(quarter);
        int g=Integer.valueOf(grade);
        if(q>4||q<0){
            Toast.makeText(grade.this, "enter valid quarter number", Toast.LENGTH_SHORT).show();
        }
        else if(g>100||g<0){
            Toast.makeText(grade.this, "enter valid grade", Toast.LENGTH_SHORT).show();
        }
        else {
            db = hlp.getWritableDatabase();
            cv2.put(Grades.CLASS_NAME, class_name);
            cv2.put(Grades.QUARTER_NUMBER, quarter);
            cv2.put(Grades.GRADE, grade); // first column of grades committed into db.
            cv2.put(Grades.STUDENT_ID, student_ID);//to know witch num of student it was.
            db.insert(Grades.TABLE_GRADES, null, cv2);
            db.close();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String Update;
        adb=new AlertDialog.Builder(this);
        final EditText et1=new EditText(this);
        Update=GradesDATA[position];
        switch (Update) {
            case "CLASS NAME":
                et1.setHint("the class name");
                adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                    /**
                     * when clicked gets out and saves class name.
                     * <p>
                     * @param dialog the dialog.
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String forUse = et1.getText().toString();
                        if (forUse.equals(null))
                            Toast.makeText(grade.this, "enter class name", Toast.LENGTH_SHORT).show();
                        else {
                            class_name = forUse;
                            dialog.dismiss();
                        }
                    }
                });
                adb.setView(et1);
                AlertDialog ad10=adb.create();
                ad10.show();
                break;
            case "QUARTER NUMBER":
                et1.setHint("the quarter number");
                adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                    /**
                     * when clicked gets out and saves quarter number.
                     * <p>
                     * @param dialog the dialog
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String forUse = et1.getText().toString();
                        if (forUse.equals(null))
                            Toast.makeText(grade.this, "enter quarter number", Toast.LENGTH_SHORT).show();
                        else {
                            quarter = forUse;
                            dialog.dismiss();
                        }
                    }
                });
                adb.setView(et1);
                AlertDialog ad11=adb.create();
                ad11.show();
                break;
            default:
                et1.setHint("the grade number");
                adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                    /**
                     * when clicked gets out and saves grade.
                     * <p>
                     * @param dialog the dialog.
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String forUse = et1.getText().toString();
                        if (forUse.equals(null))
                            Toast.makeText(grade.this, "enter grade", Toast.LENGTH_SHORT).show();
                        else {
                            grade = forUse;
                            dialog.dismiss();
                        }
                    }
                });
                adb.setView(et1);
                AlertDialog ad12=adb.create();
                ad12.show();
                break;
        }
        return false;
    }
    /**
     * saves the id of chosen student.
     * <p>
     * @param position the position in the array adapter.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Integer tmp = keys.get(position);
            student_ID = tmp.toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}