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
    ContentValues cv2=new ContentValues();
    ArrayAdapter<String> adp1,adp2;
    AlertDialog.Builder adb;
    String name,mother,father,mother_number,father_number,home_number,grade,class_name,quarter,phone_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
        listStudent =(ListView) findViewById(R.id.listStudent);
        listGrades = (ListView) findViewById(R.id.listGrade);
        listStudent.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listGrades.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adp1=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,StudentDATA);
        adp2=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,GradesDATA);
        listGrades.setAdapter(adp2);
        listStudent.setAdapter(adp1);
        listStudent.setOnItemLongClickListener(this);
        listGrades.setOnItemLongClickListener(this);
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String Update;
        adb=new AlertDialog.Builder(this);
        final EditText et1=new EditText(this);
        if (parent.getId()==(R.id.listStudent)){
            Update= StudentDATA[position];
            switch (Update) {
                case "NAME":
                    et1.setHint("enter a name");
                    adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                                /**
                                 * when clicked gets out and saves student name.
                                 * <p>
                                 * @param dialog the dialog.
                                 */
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String forUse = et1.getText().toString();
                                    if (forUse.equals(null))
                                        Toast.makeText(MainActivity.this, "enter name", Toast.LENGTH_SHORT).show();
                                    else {
                                        name = forUse;
                                        dialog.dismiss();
                                    }
                                }
                            });
                    adb.setView(et1);
                    AlertDialog ad=adb.create();
                    ad.show();
                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                    break;
                case "PHONE NUMBER":
                    et1.setHint("the phone number");
                    adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                        /**
                         * when clicked gets out and saves phone number.
                         * <p>
                         * @param dialog the dialog.
                         */
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String forUse = et1.getText().toString();
                            if (forUse.equals(null))
                                Toast.makeText(MainActivity.this, "enter phone number", Toast.LENGTH_SHORT).show();
                            else {
                                phone_number = forUse;
                                dialog.dismiss();
                            }
                        }
                    });
                    adb.setView(et1);
                    AlertDialog ad1=adb.create();
                    ad1.show();
                    Toast.makeText(MainActivity.this, home_number, Toast.LENGTH_SHORT).show();
                    break;
                case "FATHER":
                    et1.setHint("the father name");
                    adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                        /**
                         * when clicked gets out and saves father name.
                         * <p>
                         * @param dialog the dialog.
                         */
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String forUse = et1.getText().toString();
                            if (forUse.equals(null))
                                Toast.makeText(MainActivity.this, "enter father name", Toast.LENGTH_SHORT).show();
                            else {
                                father = forUse;
                                dialog.dismiss();
                            }
                        }
                    });
                    adb.setView(et1);
                    AlertDialog ad2=adb.create();
                    ad2.show();
                    Toast.makeText(MainActivity.this, father, Toast.LENGTH_SHORT).show();
                    break;
                case "MOTHER":
                    et1.setHint("the mother name");
                    adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                        /**
                         * when clicked gets out and saves mother  name.
                         * <p>
                         * @param dialog the dialog.
                         */
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String forUse = et1.getText().toString();
                            if (forUse.equals(null))
                                Toast.makeText(MainActivity.this, "enter mother name", Toast.LENGTH_SHORT).show();
                            else {
                                mother = forUse;
                                dialog.dismiss();
                            }
                        }
                    });
                    adb.setView(et1);
                    AlertDialog ad3=adb.create();
                    ad3.show();
                    Toast.makeText(MainActivity.this, mother, Toast.LENGTH_SHORT).show();
                    break;
                case "HOME NUMBER":
                    et1.setHint("the home phone number");
                    adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                        /**
                         * when clicked gets out and saves home number.
                         * <p>
                         * @param dialog the dialog.
                         */
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String forUse = et1.getText().toString();
                            if (forUse.equals(null))
                                Toast.makeText(MainActivity.this, "enter home phone number", Toast.LENGTH_SHORT).show();
                            else {
                                home_number = forUse;
                                dialog.dismiss();
                            }
                        }
                    });
                    adb.setView(et1);
                    AlertDialog ad4=adb.create();
                    ad4.show();
                    Toast.makeText(MainActivity.this, home_number, Toast.LENGTH_SHORT).show();
                    break;
                case "FATHER NUMBER":
                    et1.setHint("the father phone number");
                    adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                        /**
                         * when clicked gets out and saves father number.
                         * <p>
                         * @param dialog the dialog.
                         */
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String forUse = et1.getText().toString();
                            if (forUse.equals(null))
                                Toast.makeText(MainActivity.this, "enter father phone nuber", Toast.LENGTH_SHORT).show();
                            else {
                                father_number = forUse;
                                dialog.dismiss();
                            }
                        }
                    });
                    adb.setView(et1);
                    AlertDialog ad5=adb.create();
                    ad5.show();
                    Toast.makeText(MainActivity.this, father_number, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    et1.setHint("the mother phone number");
                    adb.setNegativeButton("done", new DialogInterface.OnClickListener() {
                        /**
                         * when clicked gets out and saves mother number.
                         * <p>
                         * @param dialog the dialog.
                         */
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String forUse = et1.getText().toString();
                            if (forUse.equals(null))
                                Toast.makeText(MainActivity.this, "enter mother phone number", Toast.LENGTH_SHORT).show();
                            else {
                                mother_number= forUse;
                                dialog.dismiss();
                            }
                        }
                    });
                    adb.setView(et1);
                    AlertDialog ad6=adb.create();
                    ad6.show();
                    Toast.makeText(MainActivity.this, mother_number, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        else{
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
                                Toast.makeText(MainActivity.this, "enter class name", Toast.LENGTH_SHORT).show();
                            else {
                                class_name = forUse;
                                dialog.dismiss();
                            }
                        }
                    });
                    adb.setView(et1);
                    AlertDialog ad10=adb.create();
                    ad10.show();
                    Toast.makeText(MainActivity.this, class_name, Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(MainActivity.this, "enter quarter number", Toast.LENGTH_SHORT).show();
                            else {
                                quarter = forUse;
                                dialog.dismiss();
                            }
                        }
                    });
                    adb.setView(et1);
                    AlertDialog ad11=adb.create();
                    ad11.show();
                    Toast.makeText(MainActivity.this, quarter, Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(MainActivity.this, "enter grade", Toast.LENGTH_SHORT).show();
                            else {
                                grade = forUse;
                                dialog.dismiss();
                            }
                        }
                    });
                    adb.setView(et1);
                    AlertDialog ad12=adb.create();
                    ad12.show();
                    Toast.makeText(MainActivity.this, grade, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
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

    public void commit(View view) {
        cv.put(Student.NAME,name);
        cv.put(Student.PHONE_NUMBER, phone_number);
        cv.put(Student.MOTHER_NUMBER, mother_number);
        cv.put(Student.FATHER, father);
        cv.put(Student.MOTHER, mother);
        cv.put(Student.HOME_NUMBER, home_number);
        cv.put(Student.FATHER_NUMBER, father_number);// first column student
        db = hlp.getWritableDatabase();
        db.insert(Student.TABLE_STUDENT,null,cv);
        db.close();
    }

    public void commit2(View view) {
        cv2.put(Grades.CLASS_NAME, class_name);
        cv2.put (Grades.QUARTER_NUMBER, quarter);
        cv2.put(Grades.GRADE, grade); // first column of grades
        db=hlp.getWritableDatabase();
        db.insert(Grades.TABLE_GRADES,null,cv2);
        db.close();
    }
}