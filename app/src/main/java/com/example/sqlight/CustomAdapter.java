package com.example.sqlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sqlight.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> grades1;

    LayoutInflater inflter;
    ArrayList<String> name;

    /**
     *
     * @param applicationContext the activity got from context.
     * @param name string array list of the students name.
     * @param grades1 the list of all the ids,class names,grades of each student.
     */
    public CustomAdapter(Context applicationContext,ArrayList<String> name, ArrayList<String> grades1) {
        this.context = applicationContext;
        this.grades1 = grades1;
        this.name=name;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    /**
     * r=build the custom view and returns it.
     * <p>
     * @param i the place on the adapter.
     * @param view the view.
     */
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_lv_layout, null);
        Spinner q1s=(Spinner)view.findViewById(R.id.q1s);
        Spinner q2s=(Spinner)view.findViewById(R.id.q2s);
        Spinner q3s=(Spinner)view.findViewById(R.id.q3s);
        Spinner q4s=(Spinner)view.findViewById(R.id.q4s);
        TextView q1=(TextView)view.findViewById(R.id.q1);
        TextView q2=(TextView)view.findViewById(R.id.q2);
        TextView q3=(TextView)view.findViewById(R.id.q3);
        TextView q4=(TextView)view.findViewById(R.id.q4);
        TextView namest=(TextView)view.findViewById(R.id.namest);
        q1.setText("quarter 1");
        q2.setText("quarter 2");
        q3.setText("quarter 3");
        q2.setText("quarter 2");// all tv are showing the quarter nums.
        namest.setText(name.get(i));
        Integer id=i;
        String id2=id.toString();
        int j=1,k=0;
        String quart1="2",quart2="2",quart3="3", quart4="4";

        ArrayList<String> g1=new ArrayList<>(),g2=new ArrayList<>(),g3=new ArrayList<>(),g4= new ArrayList<>();
        String tmp;
        while(j<grades1.size()){
            tmp=grades1.get(j);
            if(tmp.contains(id2)){
                 k=j+6;
              if(grades1.get(k).equals(quart4))  {
                  tmp = tmp.substring(j, j + 4);
                  g4.add(tmp);
              }
              else if(grades1.get(k).equals(quart3)){
                  tmp = tmp.substring(j+2, j + 4);
                  g3.add(tmp);
              }
              else if(grades1.get(k).equals(quart2)){
                  tmp = tmp.substring(j+2, j + 4);
                  g2.add(tmp);
              }
              else {
                  tmp = tmp.substring(j+2, j + 4);
                  g1.add(tmp);
              }
            }
            j=j+4;
        }// checks each id for the particular student then puts his grads of each quarter in right array list.
        ArrayAdapter<String> adpq1 = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, g1); //spinner of first quarter/
        ArrayAdapter<String> adpq2 = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, g2);// spinner of second quarter
        ArrayAdapter<String> adpq3 = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, g3);// spinner of third quarter.
        ArrayAdapter<String> adpq4 = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, g4);// spinner of fourth quarter.
        q1s.setAdapter(adpq1);
        q2s.setAdapter(adpq2);
        q3s.setAdapter(adpq3);
        q4s.setAdapter(adpq4);
        // each spinner show the grades in each quarter.
        return view;
    }
}
