package com.example.sqlight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    SQLiteDatabase db;//the SQLite data base.
    HelperDB hlp;// the class who builds the data base.
    int pos;
    String[] StudentDATA={"NAME","PHONE NUMBER","HOME NUMBER","FATHER","MOTHER","FATHER NUMBER","MOTHER NUMBER"};
    String[]GradesDATA={"CLASS NAME","QUARTER NUMBER","GRADE"};
    ListView listStudent,listGrades;
    ContentValues cv=new ContentValues();
    ArrayAdapter<String> adp1,adp2;
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
        String Data="";
        db = hlp.getWritableDatabase();
        if (parent.equals(adp1)){
            Update= StudentDATA[position];
            switch (Update) {
                case "Name":
                    cv.put(Student.NAME, Data);
                    break;
                case "PHONE NUMBER":
                    cv.put(Student.PHONE_NUMBER, Data);
                    break;
                case "FATHER":
                    cv.put(Student.FATHER, Data);
                    break;
                case "MOTHER":
                    cv.put(Student.MOTHER, Data);
                    break;
                case "HOME NUMBER":
                    cv.put(Student.HOME_NUMBER, Data);
                    break;
                case "FATHER NUMBER":
                    cv.put(Student.FATHER_NUMBER, Data);
                    break;
                case "MOTHER NUMBER":
                    cv.put(Student.MOTHER_NUMBER, Data);
            }
            db.insert(Student.TABLE_STUDENT,null,cv);

        }
        else{
            cv.clear();
            Update=GradesDATA[position];
            switch (Update) {
                case "CLASS NAME":
                    cv.put(Grades.CLASS_NAME,Data);
                    break;
                case "QUARTER NUMBER":
                   cv.put (Grades.QUARTER_NUMBER,Data);
                    break;
                case "GRADE":
                    cv.put(Grades.GRADE,Data);
            }
            db.insert(Grades.TABLE_GRADES,null,cv);
        }
        db.close();
        return false;
    }
}