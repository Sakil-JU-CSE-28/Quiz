package com.example.onlinequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button submitButton;

    private int currentQuestionIndex = 0;
    private QuizQuestion[] quizQuestions = {
            new QuizQuestion("What is the capital of France?", "Paris"),
            new QuizQuestion("Which planet is known as the Red Planet?", "Mars")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        displayQuestion(currentQuestionIndex);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion(int index) {
        if (index < quizQuestions.length) {
            questionTextView.setText(quizQuestions[index].getQuestion());
            optionsRadioGroup.clearCheck();

            RadioButton optionARadioButton = findViewById(R.id.optionARadioButton);
            RadioButton optionBRadioButton = findViewById(R.id.optionBRadioButton);
            optionARadioButton.setText(quizQuestions[index].getOptionA());
            optionBRadioButton.setText(quizQuestions[index].getOptionB());
        } else {
            showResults();
        }
    }

    private void checkAnswer() {
        int selectedId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = quizQuestions[currentQuestionIndex].getCorrectAnswer();

            if (selectedAnswer.equals(correctAnswer)) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Incorrect. The correct answer is: " + correctAnswer, Toast.LENGTH_SHORT).show();
            }

            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
        } else {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResults() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("score", currentQuestionIndex); // For simplicity, this is the score
        startActivity(intent);
        finish();
    }
}
