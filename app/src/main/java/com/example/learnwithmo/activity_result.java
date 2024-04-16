package com.example.learnwithmo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class activity_result extends AppCompatActivity {
    private TextView actTxt;
    private TextView scrollTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        // Initialize TextViews
        actTxt = findViewById(R.id.actTxt);
        scrollTxt = findViewById(R.id.scrolltxt);

        // Get the intent that started this activity
        Intent intent = getIntent();

        // Retrieve the data passed from the previous activity
        String topicName = intent.getStringExtra("topic");
        String information = intent.getStringExtra("information");

        // Set the topic name to the actTxt TextView
        actTxt.setText(topicName);

        // Set the information to the scrollTxt TextView
        scrollTxt.setText(information);
    }
}
