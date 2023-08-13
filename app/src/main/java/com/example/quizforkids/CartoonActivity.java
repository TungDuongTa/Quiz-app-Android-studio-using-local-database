package com.example.quizforkids;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizforkids.databinding.ActivityCartoonBinding;

import java.util.Collections;
import java.util.List;
import java.util.Timer;

public class CartoonActivity extends AppCompatActivity {
    TextView questions, time, totalquestions,correctCountTextView, incorrectCountTextView;;
    AppCompatButton option1, option2, option3, option4;
    Button nextbutton, endbutton;
    List<Question> questionList;
    int questionCounter;
    int questionCountTotal;

    Question currentQuestion;

    boolean answered;
    int correctAnswerIndex;

    private int correctCount = 0;
    private int incorrectCount = 0;

    private Animation fadeOutAnimation;
    private Animation fadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon);

        setTitle("Cartoon Quiz");
        questions = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextbutton = findViewById(R.id.nextbutton);
        endbutton= findViewById(R.id.endbutton);
        totalquestions= findViewById(R.id.totalQuestion);
        correctCountTextView = findViewById(R.id.correctCountTextView);
        incorrectCountTextView = findViewById(R.id.incorrectCountTextView);
        correctCountTextView.setText(String.valueOf(correctCount));
        incorrectCountTextView.setText(String.valueOf(incorrectCount));

        fadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);
        fadeOutAnimation.setDuration(500);
        fadeOutAnimation.setAnimationListener(fadeOutAnimationListener);

        fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
        fadeInAnimation.setDuration(500);
        fadeInAnimation.setAnimationListener(fadeInAnimationListener);

        QuizDBHelper dbHelper = new QuizDBHelper(this);
        questionList = dbHelper.getAllQuestions();
        Collections.shuffle(questionList);

        questionCountTotal = 4;
        showNextQuestion();

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    answered = true;
                    if (option1.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
                        option1.setBackgroundResource(R.drawable.rightanswer);
                        correctCount++;
                    } else {
                        option1.setBackgroundResource(R.drawable.wronganswer);
                        displayCorrectAnswer();
                        incorrectCount++;
                    }
                    correctCountTextView.setText(String.valueOf(correctCount));
                    incorrectCountTextView.setText(String.valueOf(incorrectCount));
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    answered = true;
                    if (option2.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
                        option2.setBackgroundResource(R.drawable.rightanswer);
                        correctCount++;
                    } else {
                        option2.setBackgroundResource(R.drawable.wronganswer);
                        displayCorrectAnswer();
                        incorrectCount++;
                    }
                    correctCountTextView.setText(String.valueOf(correctCount));
                    incorrectCountTextView.setText(String.valueOf(incorrectCount));
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    answered = true;
                    if (option3.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
                        option3.setBackgroundResource(R.drawable.rightanswer);
                        correctCount++;
                    } else {
                        option3.setBackgroundResource(R.drawable.wronganswer);
                        displayCorrectAnswer();
                        incorrectCount++;
                    }
                    correctCountTextView.setText(String.valueOf(correctCount));
                    incorrectCountTextView.setText(String.valueOf(incorrectCount));
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    answered = true;
                    if (option4.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
                        option4.setBackgroundResource(R.drawable.rightanswer);
                        correctCount++;
                    } else {
                        option4.setBackgroundResource(R.drawable.wronganswer);
                        displayCorrectAnswer();
                        incorrectCount++;
                    }
                    correctCountTextView.setText(String.valueOf(correctCount));
                    incorrectCountTextView.setText(String.valueOf(incorrectCount));
                }
            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered) {
                    option1.startAnimation(fadeOutAnimation);
                    option2.startAnimation(fadeOutAnimation);
                    option3.startAnimation(fadeOutAnimation);
                    option4.startAnimation(fadeOutAnimation);
                }
            }
        });
        endbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartoonActivity.this, HomeActivity.class);
                startActivity(intent);


            }

        });
    }

    private void showNextQuestion() {
        if(questionCounter<questionCountTotal){
            currentQuestion= questionList.get(questionCounter);
            questions.setText(currentQuestion.getQuestion());
            option1.setText(currentQuestion.getOptionA());
            option2.setText(currentQuestion.getOptionB());
            option3.setText(currentQuestion.getOptionC());
            option4.setText(currentQuestion.getOptionD());
            questionCounter++;
            totalquestions.setText(questionCounter + "/"+ questionCountTotal);
            answered=false;
            resetOptionBackground();
        }else{
            finishQuiz();
        }
    }
    private void resetOptionBackground() {
        option1.setBackgroundResource(R.drawable.button);
        option2.setBackgroundResource(R.drawable.button);
        option3.setBackgroundResource(R.drawable.button);
        option4.setBackgroundResource(R.drawable.button);
    }

    private void finishQuiz() {
        int currentAttemptPoints = correctCount * 3 - incorrectCount;
        Intent resultIntent = new Intent(CartoonActivity.this, ResultActivity.class);
        resultIntent.putExtra("quizType", "cartoon");
        resultIntent.putExtra("correctCount", correctCount);
        resultIntent.putExtra("incorrectCount", incorrectCount);
        resultIntent.putExtra("currentAttemptPoints", currentAttemptPoints);
        startActivity(resultIntent);
        correctCountTextView.setText(String.valueOf(correctCount));
        incorrectCountTextView.setText(String.valueOf(incorrectCount));
    }

    private void displayCorrectAnswer() {
        if (option1.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            option1.setBackgroundResource(R.drawable.rightanswer);
        } else if (option2.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            option2.setBackgroundResource(R.drawable.rightanswer);
        } else if (option3.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            option3.setBackgroundResource(R.drawable.rightanswer);
        } else if (option4.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            option4.setBackgroundResource(R.drawable.rightanswer);
        }
    }

    private Animation.AnimationListener fadeOutAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            showNextQuestion();
            option1.startAnimation(fadeInAnimation);
            option2.startAnimation(fadeInAnimation);
            option3.startAnimation(fadeInAnimation);
            option4.startAnimation(fadeInAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private Animation.AnimationListener fadeInAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };
}
