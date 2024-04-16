package com.example.learnwithmo;

import androidx.appcompat.app.AppCompatActivity;

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

public class activity_vocabulary extends AppCompatActivity {
    private ListView listViewVocabulary;
    private Button opBtnV ;
   private TextView opinion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        setViews();
        buttonAction();//button Action
        // Sample vocabulary topics and their information/examples
        final String[] vocabularyTopics = {"Animals", "Fruits", "Colors", "Numbers", "Family"};

        final String[] vocabularyInformation =fillVocabularyInformation();

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vocabularyTopics);

        // Set the ArrayAdapter on the ListView
        listViewVocabulary.setAdapter(adapter);
          // List Actions call
         listActions( vocabularyTopics, vocabularyInformation);
    }
    public void listActions(String[] vocabularyTopics,String[] vocabularyInformation){
        // Set click listener for ListView items
        listViewVocabulary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String selectedTopic = vocabularyTopics[position];
                String selectedInformation = vocabularyInformation[position];

                // Start DetailActivity and pass selected data
                Intent intent = new Intent(activity_vocabulary.this, activity_result.class);
                intent.putExtra("topic", selectedTopic);
                intent.putExtra("information", selectedInformation);
                startActivity(intent);
            }
        });
    }
    public void setViews(){
        listViewVocabulary = findViewById(R.id.listViewVocabulary);
        opBtnV = findViewById(R.id.opBtnG);
        opinion = findViewById(R.id.openionVedt);
    }
    // I well use this Method later on if i want to update it
    public void buttonAction(){
        opBtnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Assume you have an EditText named opinionEditText for user input
                String userOpinion = opinion.getText().toString();
                // Store the user opinion using SharedPreferences
                SharedPreferences preferences = getSharedPreferences("MyOpinions", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("grammarOpinion", userOpinion);
                editor.apply();

                Toast.makeText(activity_vocabulary.this, "Your Opinion Was Submitted ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String[] fillVocabularyInformation() {
        String[] vocabularyInformation = new String[5];

        vocabularyInformation[0] = "\nAnimals\n\n" +
                "Definition:\n Animals are multicellular organisms that form the kingdom Animalia.\n\n" +
                "Vocabulary Examples:\n dog, cat, elephant, lion\n\n" +
                "Example:\n Animals play an essential role in the ecosystem by maintaining balance and diversity.\n\n" +
                "Additional Examples:\n tiger, bear, giraffe";

        vocabularyInformation[1] = "\nFruits\n\n" +
                "Definition:\n Fruits are the mature ovary or ovaries of one or more flowers.\n\n" +
                "Vocabulary Examples:\n apple, banana, orange, mango\n\n" +
                "Example:\n Fruits provide essential vitamins and nutrients for a healthy diet.\n\n" +
                "Additional Examples:\n pineapple, grape, peach";

        vocabularyInformation[2] = "\nColors\n\n" +
                "Definition:\n Colors are the characteristic of human visual perception described through color categories.\n\n" +
                "Vocabulary Examples:\n red, blue, green, yellow\n\n" +
                "Example:\n Colors can evoke various emotions and have cultural significance in different societies.\n\n" +
                "Additional Examples:\n purple, orange, pink";

        vocabularyInformation[3] = "\nNumbers\n\n" +
                "Definition:\n Numbers are mathematical objects used to count, measure, and label.\n\n" +
                "Vocabulary Examples:\n one, two, three, four, five\n\n" +
                "Example:\n Numbers are fundamental in mathematics and are used in everyday life for various purposes.\n\n" +
                "Additional Examples:\n six, seven, eight";

        vocabularyInformation[4] = "\nFamily\n\n" +
                "Definition:\n Family is a group of people who are related to each other, especially parents and their children.\n\n" +
                "Vocabulary Examples:\n mother, father, brother, sister\n\n" +
                "Example:\n Family provides emotional support and plays a vital role in shaping an individual's identity.\n\n" +
                "Additional Examples:\n aunt, uncle, cousin";

        return vocabularyInformation;
    }

}