package com.example.learnwithmo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class activity_main extends AppCompatActivity {
    TextView txtsent;
    Button vocBtn;
    Button graBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_show);
        setViews();

        // Retrieve name from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String msg = sharedPreferences.getString("name", "");
        txtsent.setText("Hi" + " " + msg + " !");
        setActions();

    }
    //Action method that is in the xml called
    public void startQuizActivity(View view) {
        Intent intent = new Intent(activity_main.this, activity_Quiz.class);
        startActivity(intent);
    }

    public void setActions(){
    vocBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity_main.this, activity_vocabulary.class);
            startActivity(intent);
        }
    });

    graBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity_main.this, activity_grammar.class);
            startActivity(intent);
        }
    });
}
    public void setViews() {
        txtsent = findViewById(R.id.edtName);
        vocBtn = findViewById(R.id.btnVocabulary);
        graBtn = findViewById(R.id.btnGrammar);
    }
}
