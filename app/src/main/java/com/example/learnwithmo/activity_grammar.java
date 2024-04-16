package com.example.learnwithmo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class activity_grammar extends AppCompatActivity {
    private ListView listViewGrammar;
    private Button opBtnG;
    private TextView opinion;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        setViews();
        buttonAction();//Button Action
        // Sample grammar topics and their information/examples
        final String[] grammarTopics = {"Verb Tenses", "Nouns", "Adjectives", "Adverbs", "Pronouns"};
        final String[] grammarInformation = fillGrammarInformation();


        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, grammarTopics);
        // Set the ArrayAdapter on the ListView
        listViewGrammar.setAdapter(adapter);
        //List Action calling
        listActions(grammarTopics, grammarInformation);

    }
    public void listActions(String[] grammarTopics,String[] grammarInformation){
        // Set click listener for ListView items
        listViewGrammar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String selectedTopic = grammarTopics[position];
                String selectedInformation = grammarInformation[position];

                // Start DetailActivity and pass selected data
                Intent intent = new Intent(activity_grammar.this, activity_result.class);
                intent.putExtra("topic", selectedTopic);
                intent.putExtra("information", selectedInformation);
                startActivity(intent);
            }
        });
    }
    public void setViews(){
        listViewGrammar = findViewById(R.id.listViewGrammer);
        opBtnG = findViewById(R.id.opBtnG);
        opinion = findViewById(R.id.openionGedt);
    }
    // I well use this Method later on if i want to update it
    public void buttonAction(){
        opBtnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Assume you have an EditText named opinionEditText for user input
                String userOpinion = opinion.getText().toString();
                // Store the user opinion using SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyOpinions", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("grammarOpinion", userOpinion);
                editor.apply();

                Toast.makeText(activity_grammar.this, "Your Opinion Was Submitted ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String[] fillGrammarInformation() {
        String[] grammarInformation = new String[5];

        grammarInformation[0] = "\nVerb tenses express the time at which an action takes place.\n\n" +
                "Examples: She runs (present), She ran (past), She will run (future).\n\n" +
                "Additional Examples: He is swimming (present continuous), He swam yesterday (past simple), He will be swimming tomorrow (future continuous).";

        grammarInformation[1] = "\nNouns are words that represent people, places, things, or ideas.\n\n" +
                "Examples: cat, house, book, happiness.\n\n" +
                "Additional Examples: dog, city, chair, love.";

        grammarInformation[2] = "\nAdjectives are words that describe or modify nouns and pronouns.\n\n" +
                "Examples: beautiful, tall, delicious, intelligent.\n\n" +
                "Additional Examples: colorful, friendly, big, interesting.";

        grammarInformation[3] = "\nAdverbs are words that modify verbs, adjectives, or other adverbs.\n\n" +
                "Examples: quickly, quietly, happily, extremely.\n\n" +
                "Additional Examples: slowly, loudly, sadly, very.";

        grammarInformation[4] = "\nPronouns are words that can function as a noun phrase.\n\n" +
                "Examples: I, you, he, she, it.\n\n" +
                "Additional Examples: we, they, me, him, her.";

        return grammarInformation;
    }

}