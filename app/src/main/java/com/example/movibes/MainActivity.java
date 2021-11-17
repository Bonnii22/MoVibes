package com.example.movibes;

import android.app.ListActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends ListActivity {


    private String details[] = {
            findViewById(R.id.txtComplaintDetails).toString()
        };


    private Integer imageid[] = {
            R.id.imgCaptured
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting header
        TextView textView = new TextView(this);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText("List of Events");

        ListView listView=(ListView)findViewById(android.R.id.list);
        listView.addHeaderView(textView);

        // For populating list data
        CustomEventsList customCountryList = new CustomEventsList(this, details, imageid);
        listView.setAdapter(customCountryList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"You Selected "+details[position-1]+ " as event",Toast.LENGTH_SHORT).show();
            }
        });
    }


}