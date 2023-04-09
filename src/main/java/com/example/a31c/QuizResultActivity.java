package com.example.a31c;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a31c.R;

public class QuizResultActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView scoreTextView;
    private Button newQuizButton;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz_result);

        // Get user name and score from intent extras
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 5);


        // Initialize UI elements
        nameTextView = findViewById(R.id.name_text_view);
        scoreTextView = findViewById(R.id.score_text_view);

        // Set text for name and score
        nameTextView.setText("Congratulations " + name + "!");
        scoreTextView.setText("Your Score: " + score);

        newQuizButton = findViewById(R.id.new_quiz_button);
        finishButton = findViewById(R.id.finish_button);

        // Set click listener for "Take New Quiz" button
        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the quiz activity to take a new quiz
                Intent intent = new Intent(QuizResultActivity.this, QuizActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("score", score);
                intent.putExtra("totalQuestions", totalQuestions);
                startActivity(intent);
                // Finish the quiz result activity
                finish();
            }
        });

        // Set click listener for "Finish" button
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the app
                finishAffinity();
            }
        });

        // Get the quiz score and total questions from the intent



        // Set the score text
        scoreTextView.setText(score + "/" + totalQuestions);
    }
}
