package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;


public class AnimalActivity extends AppCompatActivity {
    private TextView questionTextView, CurrentQuestion;
    private ImageView animalImageView;
    private EditText answerEditText;
    private Button submitButton,endButton;
    private TextView correctCountTextView;
    private TextView incorrectCountTextView;
    private List<Question1> animalQuestions;
    private int currentQuestionIndex;
    private int correctCount;
    private int incorrectCount;
    private Animation fadeOutAnimation;
    private Animation fadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        setTitle("Animal Quiz");

        // Initialize views
        endButton=findViewById(R.id.button2);
        questionTextView = findViewById(R.id.questionTextView);
        animalImageView = findViewById(R.id.animalImageView);
        answerEditText = findViewById(R.id.answerEditText);
        submitButton = findViewById(R.id.submitButton);
        correctCountTextView = findViewById(R.id.correct);
        incorrectCountTextView = findViewById(R.id.incorrect);
        CurrentQuestion= findViewById(R.id.CurrentQs);

        // Get animal questions from the database
        QuizDBHelper dbHelper = new QuizDBHelper(this);
        animalQuestions = dbHelper.getAllAnimalQuestions(this);
        Collections.shuffle(animalQuestions);

        // Set current question index
        currentQuestionIndex = 0;
        correctCount = 0;
        incorrectCount = 0;

        fadeOutAnimation = createFadeOutAnimation();
        fadeInAnimation = createFadeInAnimation();

        // Display the first question
        displayQuestion();

        // Set onClickListener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animateFadeOut();
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalActivity.this, HomeActivity.class);
                startActivity(intent);


            }

        });
    }

    private void displayQuestion() {
        if (currentQuestionIndex < 4) {
            Question1 currentQuestion = animalQuestions.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestion());

            String imageName = currentQuestion.getImage(); // Image name without the file extension
            String imageFileName = imageName; // Complete image file name with the ".png" extension

            // Get the resource ID of the image dynamically
            int imageResourceId = getResources().getIdentifier(imageFileName, "drawable", getPackageName());

            // Set the image resource
            animalImageView.setImageResource(imageResourceId);


            answerEditText.setText("");
            correctCountTextView.setText("Correct: " + correctCount);
            incorrectCountTextView.setText("Incorrect: " + incorrectCount);
            CurrentQuestion.setText(currentQuestionIndex+1 + "/"+ "4");
        }
    }

    private void animateFadeOut() {
        questionTextView.animate().alpha(0f).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                checkAnswer();
                displayQuestion();
                animateFadeIn();
            }
        }).start();

        animalImageView.animate().alpha(0f).setDuration(500).start();
    }

    private void animateFadeIn() {
        questionTextView.animate().alpha(1f).setDuration(500).start();
        animalImageView.animate().alpha(1f).setDuration(500).start();
    }

    private Animation createFadeOutAnimation() {
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(500);
        return animation;
    }

    private Animation createFadeInAnimation() {
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(500);
        return animation;
    }


    private void checkAnswer() {
        if (currentQuestionIndex < 4) {
            Question1 currentQuestion = animalQuestions.get(currentQuestionIndex);
            String userAnswer = answerEditText.getText().toString().trim();
            String correctAnswer = currentQuestion.getAnswer();


            // Check if the user's answer matches the correct answer
            boolean isCorrect = userAnswer.equalsIgnoreCase(correctAnswer);


            // Increment the correct or incorrect count based on the user's answer
            if (isCorrect) {
                correctCount++;
            } else {
                incorrectCount++;
            }


            // Move to the next question
            currentQuestionIndex++;
            if (currentQuestionIndex < 4) {
                displayQuestion();
            } else {
                int currentAttemptPoints = correctCount * 3 - incorrectCount;
                // Navigate to the results screen or perform any other action you want
                // For example:
                Intent intent = new Intent(AnimalActivity.this, ResultActivity.class);
                intent.putExtra("currentAttemptPoints", currentAttemptPoints);
                intent.putExtra("quizType", "animal");
                intent.putExtra("correctCount", correctCount);
                intent.putExtra("incorrectCount", incorrectCount);
                startActivity(intent);
                finish();
            }
        }
    }

}
