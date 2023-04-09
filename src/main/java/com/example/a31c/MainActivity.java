package com.example.a31c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        Button startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            if (name.isEmpty()) {
                name = "Guest";
            }
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
        });
    }
}