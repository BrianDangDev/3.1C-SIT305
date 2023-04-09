package com.example.a31c;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private Button submitButton;
    private ProgressBar progressBar;
    private TextView progressTextView;

    private String name;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[] questions = {"Question 1 : What is the result of 1 + 1",
            "Question 2: What grade are you targeting this trimester?",
            "Question 3: What is the best unit at Deakin Uni?",
            "Question 4: What year is it?",
            "Question 5: What is the best social media for networking?"};
    private String[][] answers = {
            {"0", "1", "2", "3"},
            {"HD", "D", "C", "P"},
            {"SIT305", "SIT210", "SIT315", "SIT306"},
            {"2001", "2022", "2023", "2021"},
            {"TikTok", "Facebook", "Linkedin", "Instagram"}};
    private int[] correctAnswers = {2, 0,0, 2, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get user name from intent extra
        name = getIntent().getStringExtra("name");

        // Initialize UI elements
        questionTextView = findViewById(R.id.question_text_view);
        answerRadioGroup = findViewById(R.id.answer_radio_group);
        submitButton = findViewById(R.id.submit_button);
        progressBar = findViewById(R.id.progress_bar);
        progressTextView = findViewById(R.id.progress_text_view);

        // Update UI with first question
        updateQuestion();

        // Set up click listener for submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if an answer was selected
                if (answerRadioGroup.getCheckedRadioButtonId() == -1) {
                    return;
                }

                // Get selected answer index
                int selectedAnswerIndex = answerRadioGroup.indexOfChild(findViewById(answerRadioGroup.getCheckedRadioButtonId()));

                // Check if answer is correct
                if (selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
                    score++;
                    answerRadioGroup.getChildAt(selectedAnswerIndex).setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    answerRadioGroup.getChildAt(selectedAnswerIndex).setBackgroundColor(getResources().getColor(R.color.red));
                    answerRadioGroup.getChildAt(correctAnswers[currentQuestionIndex]).setBackgroundColor(getResources().getColor(R.color.green));
                }

                // Disable answer radio group and submit button
                for (int i = 0; i < answerRadioGroup.getChildCount(); i++) {
                    answerRadioGroup.getChildAt(i).setEnabled(false);
                }
                submitButton.setEnabled(false);

                // Update progress bar and check if quiz is finished
                currentQuestionIndex++;
                progressBar.setProgress((int) (((float) currentQuestionIndex / questions.length) * 100));
                if (currentQuestionIndex == questions.length) {
                    // Launch quiz result activity
                    Intent intent = new Intent(QuizActivity.this, QuizResultActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                } else {
                    // Move to next question
                    questionTextView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            updateQuestion();
                        }
                    }, 1500); // Delay before showing next question
                }
                // Update progress text
                TextView progressTextView = findViewById(R.id.progress_text_view);
                int progress = (int) (((float) currentQuestionIndex / questions.length) * 100);
                progressTextView.setText(String.format("%d%%", progress));
            }
        });
    }


    private void updateQuestion() {
        // Reset answer radio group and submit button
        answerRadioGroup.clearCheck();
        for (int i = 0; i < answerRadioGroup.getChildCount(); i++) {
            answerRadioGroup.getChildAt(i).setEnabled(true);
            answerRadioGroup.getChildAt(i).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        submitButton.setEnabled(true);

        // Update question text and answer options
        questionTextView.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < answerRadioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) answerRadioGroup.getChildAt(i);
            radioButton.setText(answers[currentQuestionIndex][i]);
        }
    }
}
