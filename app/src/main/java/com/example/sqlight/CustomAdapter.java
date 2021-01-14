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
    ArrayList<String> grades2;
    ArrayList<String> grades3;
    ArrayList<String> grades4;
    LayoutInflater inflter;
    ArrayList<String> name;

    public CustomAdapter(Context applicationContext,ArrayList<String> name, ArrayList<String> grades1,ArrayList<String>grades2,ArrayList<String>grades3,ArrayList<String>grades4) {
        this.context = applicationContext;
        this.grades1 = grades1;
        this.grades2 = grades2;
        this.grades3 = grades3;
        this.grades4 = grades4;
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
        namest.setText(name);
        ArrayAdapter<String> adpq1 = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, grades1);
        ArrayAdapter<String> adpq2 = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, grades2);
        ArrayAdapter<String> adpq3 = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, grades3);
        ArrayAdapter<String> adpq4 = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, grades4);
        q1s.setAdapter(adpq1);
        q2s.setAdapter(adpq2);
        q3s.setAdapter(adpq3);
        q4s.setAdapter(adpq4);
        // each spinner show the grades in each quarter.
        return view;
    }
}
