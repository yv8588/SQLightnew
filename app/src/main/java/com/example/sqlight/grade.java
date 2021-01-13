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

public class grade extends AppCompatActivity implements AdapterView.OnItemLongClickListener,AdapterView.OnItemSelectedListener{
ListView listGrades;
SQLiteDatabase db;//the SQLite data base.
HelperDB hlp;// the class who builds the data base.
String[]GradesDATA={"CLASS NAME","QUARTER NUMBER","GRADE"};
AlertDialog.Builder adb;
String grade,class_name,quarter;
ContentValues cv2=new ContentValues();
Cursor crsr;
Spinner stud;
int student_ID;
ArrayList<Integer> keys = new ArrayList<>();// students key id.
ArrayList<String> names= new ArrayList<>();// students name.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        stud=(Spinner)findViewById(R.id.namespin);
        listGrades = (ListView) findViewById(R.id.listGrade);
        listGrades.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, GradesDATA);// the adapter for the second list/
        listGrades.setAdapter(adp2);
        listGrades.setOnItemLongClickListener(this);
        ArrayAdapter<String>spin=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,names);
        stud.setAdapter(spin);
        stud.setOnItemSelectedListener(this);
        db = hlp.getWritableDatabase();
        String TABLE = TABLE_STUDENT;
        String[] columns = {Student.KEY_ID, Student.NAME};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;
        crsr = db.query(TABLE, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        int col1 = crsr.getColumnIndex(Student.KEY_ID);
        int col2 = crsr.getColumnIndex(Student.NAME);
        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            int key = crsr.getInt(col1);
            String name = crsr.getString(col2);
            keys.add(key);
            names.add(name);
            crsr.moveToNext();
        }
        crsr.close();
        db.close();

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
        else if(s.equals("main activity")) {
           si=new Intent(this,MainActivity.class);
           startActivity(si);
        }
        else{
            si=new Intent(this,filterdShowdb.class);
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
        cv2.put(Grades.CLASS_NAME, class_name);
        cv2.put (Grades.QUARTER_NUMBER, quarter);
        cv2.put(Grades.GRADE, grade); // first column of grades committed into db.
        cv2.put(Grades.STUDENT_ID,student_ID);//to know witch num of student it was.
        db=hlp.getWritableDatabase();
        db.insert(Grades.TABLE_GRADES,null,cv2);
        db.close();
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
                Toast.makeText(grade.this, class_name, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(grade.this, quarter, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(grade.this, grade, Toast.LENGTH_SHORT).show();
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
        student_ID=keys.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}