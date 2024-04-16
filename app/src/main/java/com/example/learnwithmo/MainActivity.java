package com.example.learnwithmo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spnEdu;
    String name;
    Button btnSub;
    String msg;
    // SharedPreferences key
    private static final String PREF_NAME_KEY = "name";
    // SharedPreferences instance
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews(); // Call setViews() to initialize views
        populateSpinner(); // Call populateSpinner() to populate the spinner
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        name = sharedPreferences.getString(PREF_NAME_KEY, "");
        buttonAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the name from SharedPreferences when the activity resumes
        name = sharedPreferences.getString(PREF_NAME_KEY, "");
    }

    public void buttonAction() {
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the name and age from the EditText fields
                String name = ((EditText) findViewById(R.id.edtName)).getText().toString();
                String age = ((EditText) findViewById(R.id.edtAge)).getText().toString();

                // Check if the name or age is empty
                if (name.isEmpty() || age.isEmpty()) {
                    // Show a toast message indicating that the information needs to be filled
                    Toast.makeText(MainActivity.this, "Please fill in both name and age", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Store the name using SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(PREF_NAME_KEY, name);
                editor.apply();

                // Show a welcome message
                String welcomeMessage = "Welcome " + name;
                Toast.makeText(MainActivity.this, welcomeMessage, Toast.LENGTH_SHORT).show();

                // Start the next activity
                Intent intent = new Intent(MainActivity.this, activity_main.class);
                startActivity(intent);
            }
        });
    }

    public void setViews() {
        btnSub = findViewById(R.id.btnSub);
        spnEdu = findViewById(R.id.spnEdu);
    }

    private void populateSpinner() {
        String[] EducationLevels = {"Preschool/Kindergarten",
                "Elementary School",
                " Middle School",
                "High School"};
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, EducationLevels);
        // Apply the adapter to the spinner
        spnEdu.setAdapter(adapter);
    }
}

