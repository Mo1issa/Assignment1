package com.example.learnwithmo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class activity_Quiz extends AppCompatActivity {
    RadioGroup question1RadioGroup,question2RadioGroup
            ,question3RadioGroup,question4RadioGroup,
            question5RadioGroup;
    TextView grads;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Button submitButton = findViewById(R.id.btnSub2);
        grads =  findViewById(R.id.grade);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Disable the button to prevent multiple clicks
                submitButton.setEnabled(false);
                Log.d("MyTag", "This is a one message"); // Debug log

                // Get references to all the RadioGroups
                 question1RadioGroup = findViewById(R.id.question1RadioGroup);
                 question2RadioGroup = findViewById(R.id.question2RadioGroup);
                 question3RadioGroup = findViewById(R.id.question3RadioGroup);
                 question4RadioGroup = findViewById(R.id.question4RadioGroup);
                 question5RadioGroup = findViewById(R.id.question5RadioGroup);
                Log.d("MyTag", "Submit button clicked"); // Debug log

                // Check if any question is not answered
                if (isAnyQuestionNotAnswered(question1RadioGroup, question2RadioGroup, question3RadioGroup, question4RadioGroup, question5RadioGroup)) {
                    Toast.makeText(activity_Quiz.this, "Please answer all questions", Toast.LENGTH_SHORT).show();
                    // Re-enable the button
                    submitButton.setEnabled(true);
                    return;
                }

                // Calculate score
                int score = calculateScore(question1RadioGroup, question2RadioGroup, question3RadioGroup, question4RadioGroup, question5RadioGroup);
                Log.d("MyTag", "Score calculated: " + score); // Debug log

                // Check if the quiz is incomplete
                if (score == -1) {
                    // Re-enable the button
                    submitButton.setEnabled(true);
                    return;
                }

                // Display grading
                displayGrading(score);
                Log.d("MyTag", "Grading displayed: " + score); // Debug log
                String msg = Grading(score);
                grads.setText("( " + msg + " )");
                grads.setVisibility(View.VISIBLE);

                // Re-enable the buttons
                submitButton.setEnabled(true);
            }
        });


    }

    private boolean isAnyQuestionNotAnswered(RadioGroup... radioGroups) {
        for (RadioGroup radioGroup : radioGroups) {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                return true; // Not answered
            }
        }
        return false; // All questions answered
    }

    private int calculateScore(RadioGroup... radioGroups) {
        int score = 0;
        boolean anyQuestionNotAnswered = false; // Flag to track if any question is not answered
        for (RadioGroup radioGroup : radioGroups) {
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            Log.d("CheckedRadioButtonId", "RadioGroup ID: " + radioGroup.getId() + ", Checked ID: " + checkedRadioButtonId);
            if (checkedRadioButtonId != -1) { // Check if any radio button is checked
                RadioButton radioButton = findViewById(checkedRadioButtonId);
                if (radioButton != null) {
                    String answer = radioButton.getText().toString();
                    Log.d("Answer", "Answer for RadioGroup ID " + radioGroup.getId() + ": " + answer);
                    // Compare the selected answer with the correct answer and update score accordingly
                    int questionNumber = getQuestionNumber(radioGroup.getId()); // Extract the question number from the RadioGroup ID
                    Log.d("QuestionNumber", "Question number for RadioGroup ID " + radioGroup.getId() + ": " + questionNumber);
                    if (isCorrectAnswer(questionNumber, answer)) {
                        score++;
                    }
                }
            } else {
                // Set the flag if any question is not answered
                anyQuestionNotAnswered = true;
            }
        }
        // Check if any question is not answered and return -1 if so
        if (anyQuestionNotAnswered) {
            Toast.makeText(this, "Please answer all questions", Toast.LENGTH_SHORT).show();
            return -1; // Return -1 to indicate incomplete quiz
        }
        return score;
    }

    private int getQuestionNumber(int radioGroupId) {
        String idString = getResources().getResourceEntryName(radioGroupId);
        // Extract the numeric part between "question" and "RadioGroup"
        String numberString = idString.substring(8, idString.length() - "RadioGroup".length());
        return Integer.parseInt(numberString);
    }

    private boolean isCorrectAnswer(int questionNumber, String answer) {
        switch (questionNumber) {
            case 1:
                return answer.equals("D) Animals"); // Correct answer for Question 1
            case 2:
                return answer.equals("B) Fruits"); // Correct answer for Question 2
            case 3:
                return answer.equals("B) Adjectives"); // Correct answer for Question 3
            case 4:
                return answer.equals("A) Verb Tenses"); // Correct answer for Question 4
            case 5:
                return answer.equals("D) Nouns"); // Correct answer for Question 5
            default:
                return false; // Placeholder for questions other than 1-5
        }
    }

    private void displayGrading(int score) {
        String grading;
        if (score == 5) {
            grading = "Grade: A+";
        } else if (score >= 3) {
            grading = "Grade: B";
        } else if (score >= 1) {
            grading = "Grade: C";
        } else {
            grading = "Grade: F";
        }
        Toast.makeText(this, grading, Toast.LENGTH_SHORT).show();
    }
    private String Grading(int score) {
        String grading;
        if (score == 5) {
            grading = "Grade: A+";
        } else if (score >= 3) {
            grading = "Grade: B";
        } else if (score >= 1) {
            grading = "Grade: C";
        } else {
            grading = "Grade: F";
        }
       return grading;
    }
}
