package com.example.movibes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomEventsList extends ArrayAdapter<String> {
    private String[] details;
    private Integer[] imageid;
    private Activity context;

    public CustomEventsList(Activity context, String[] details, Integer[] imageid) {
        super(context, R.layout.activity_row_item, details);
        this.context = context;
        this.details = details;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.activity_row_item, null, true);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imgposter);

        TextView textViewCountry = (TextView) row.findViewById(R.id.establishmentname);
        textViewCountry.setText(details[position]);
        imageFlag.setImageResource(imageid[position]);
        return  row;
    }
}
