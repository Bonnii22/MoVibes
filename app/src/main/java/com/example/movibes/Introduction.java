package com.example.movibes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Introduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Button btnOwner = (Button) findViewById(R.id.btnOwner);
        Button btnUser = (Button) findViewById(R.id.btnUser);
        TextView textView = (TextView) findViewById(R.id.intro);
        textView.setText("Welcome, " + R.id.txtEmail);

        btnOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Introduction.this, FileCurrentComplaintActivity.class);
                startActivity(intent);


            }
        });
    }
}
