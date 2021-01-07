package com.example.sqlight;
/**
 * @author yoad wolfson.
 * @version 1.0
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
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    SQLiteDatabase db;//the SQLite data base.
    HelperDB hlp;// the class who builds the data base.
    String[] StudentDATA={"NAME","PHONE NUMBER","HOME NUMBER","FATHER","MOTHER","FATHER NUMBER","MOTHER NUMBER"};
    String[]GradesDATA={"CLASS NAME","QUARTER NUMBER","GRADE"};
    ListView listStudent,listGrades;
    ContentValues cv=new ContentValues();
    ArrayAdapter<String> adp1,adp2;
    AlertDialog.Builder adb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
        setContentView(R.layout.activity_main);
        listStudent = (ListView) findViewById(R.id.listStudent);
        listGrades = (ListView) findViewById(R.id.listGrade);
        listStudent.setChoiceMode(listStudent.CHOICE_MODE_SINGLE);
        listGrades.setChoiceMode(listGrades.CHOICE_MODE_SINGLE);
        adp1=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,StudentDATA);
        adp2=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,GradesDATA);
        listStudent.setOnItemLongClickListener(this);
        listGrades.setOnItemLongClickListener(this);
    }
    /**
     * creating context menu
     * <p>
     * @param menu the context menu.
     * @param v the xml view.
     * @param menuInfo info.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    /**
     * reacting to users choose int the menu.
     * <p>
     * @param item the view of  the  row that got chosen.
     * @return boolean whether the method was operating.
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String Update;
         String[] Data = {""};
        adb=new AlertDialog.Builder(this);
        db = hlp.getWritableDatabase();
        final EditText et2=new EditText(this);
        final EditText et1=new EditText(this);
        adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
            /**
             * when clicked gets out and saves name.
             * <p>
             * @param dialog
             * @param which
             */
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String forUse = et1.getText().toString();
                if(forUse.equals(null))
                    Toast.makeText(MainActivity.this,"enter a name",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                Data[0] = forUse;
            }
        });
        if (parent.equals(adp1)){
            Update= StudentDATA[position];
            switch (Update) {
                case "Name":
                    et1.setHint("enter a name");
                    adb.setView(et1);
                    AlertDialog ad=adb.create();
                    ad.show();
                    cv.put(Student.NAME, Data[0]);
                    break;
                case "PHONE NUMBER":
                    et1.setHint("the phone number");
                    adb.setView(et1);
                    AlertDialog ad1=adb.create();
                    ad1.show();
                    cv.put(Student.PHONE_NUMBER, Data[0]);
                    break;
                case "FATHER":
                    et1.setHint("the father name");
                    adb.setView(et1);
                    cv.put(Student.FATHER, Data[0]);
                    break;
                case "MOTHER":
                    et1.setHint("the mother name");
                    adb.setView(et1);
                    cv.put(Student.MOTHER, Data[0]);
                    break;
                case "HOME NUMBER":
                    et1.setHint("the home phone number");
                    adb.setView(et1);
                    cv.put(Student.HOME_NUMBER, Data[0]);
                    break;
                case "FATHER NUMBER":
                    et1.setHint("the father phone number");
                    adb.setView(et1);
                    cv.put(Student.FATHER_NUMBER, Data[0]);
                    break;
                default:
                    et1.setHint("the mother phone number");
                    adb.setView(et1);
                    cv.put(Student.MOTHER_NUMBER, Data[0]);
            }
            db.insert(Student.TABLE_STUDENT,null,cv);

        }
        else{
            cv.clear();
            Update=GradesDATA[position];
            switch (Update) {
                case "CLASS NAME":
                    et1.setHint("the class name");
                    adb.setView(et1);
                    cv.put(Grades.CLASS_NAME, Data[0]);
                    break;
                case "QUARTER NUMBER":
                    et1.setHint("the quarter number");
                    adb.setView(et1);
                   cv.put (Grades.QUARTER_NUMBER, Data[0]);
                    break;
                case "GRADE":
                    et1.setHint("the grade number");
                    adb.setView(et1);
                    cv.put(Grades.GRADE, Data[0]);
            }
            db.insert(Grades.TABLE_GRADES,null,cv);
        }
        db.close();
        return false;
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
        else if (s.equals("show data base")){
            si=new Intent(this,showdb.class);
            startActivity(si);
        }
        else{
            si=new Intent(this,filterdShowdb.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}