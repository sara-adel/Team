package com.example.sara.team.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sara.team.R;

public class About extends AppCompatActivity {

    TextView mission, vission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        mission = (TextView) findViewById(R.id.mission);
        vission = (TextView) findViewById(R.id.vission);

        mission.setText("To create economic and social value on a global scale by providing a trusted online workplace to connect, " +
                "collaborate, and succeed.");
        vission.setText("To connect businesses with great talent faster than ever before.");

    }
}
