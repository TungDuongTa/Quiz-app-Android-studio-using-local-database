package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    TextView Point, Correct, Incorrect,OverallPoints;;
    Button ReAttempt, Finishbnt, DifQuiz;
    String quizType;
    DBHelper dbHelper;
    private DBHelper DBHelper;
    private AttemptDBHelper dBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setTitle("Result");

        dbHelper = new DBHelper(this);
        OverallPoints= findViewById(R.id.overallPoints);
        Point = findViewById(R.id.point);
        Correct = findViewById(R.id.correctTime);
        Incorrect = findViewById(R.id.IncorrectTime);
        ReAttempt = findViewById(R.id.reAttempt);
        Finishbnt = findViewById(R.id.Finish);
        DifQuiz = findViewById(R.id.area);
        DBHelper = new DBHelper(this);
        dBHelper = new AttemptDBHelper(this);
        Intent intent = getIntent();
        int points = intent.getIntExtra("currentAttemptPoints", 0);
        int correctCount = intent.getIntExtra("correctCount", 0);
        int incorrectCount = intent.getIntExtra("incorrectCount", 0);
        quizType = intent.getStringExtra("quizType");

        // Display the values in the TextViews
        Point.setText(String.valueOf(points));
        Correct.setText(String.valueOf(correctCount));
        Incorrect.setText(String.valueOf(incorrectCount));

        // Save the quiz attempt
        saveQuizAttempt(points, correctCount, incorrectCount);

        ReAttempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the corresponding quiz activity again
                if (quizType.equals("cartoon")) {
                    Intent cartoonIntent = new Intent(ResultActivity.this, CartoonActivity.class);
                    startActivity(cartoonIntent);
                } else if (quizType.equals("animal")) {
                    Intent animalQuizIntent = new Intent(ResultActivity.this, AnimalActivity.class);
                    startActivity(animalQuizIntent);
                }
                finish(); // Finish the ResultActivity
            }
        });

        Finishbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(ResultActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish(); // Finish the ResultActivity
            }
        });

        DifQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent;
                if (quizType.equals("animal")) {
                    quizIntent = new Intent(ResultActivity.this, CartoonActivity.class);
                } else if (quizType.equals("cartoon")) {
                    quizIntent = new Intent(ResultActivity.this, AnimalActivity.class);
                } else {
                    // Handle other quiz types or default behavior
                    return;
                }
                startActivity(quizIntent);
                finish(); // Finish the ResultActivity
            }
        });
        AttemptDBHelper dbHelper = new AttemptDBHelper(this);
        //int overallPoints = dbHelper.getOverallPoints();
        //OverallPoints = findViewById(R.id.overallPoints);
        //OverallPoints.setText("" + overallPoints);
        List<Attempt> attempts = dbHelper.getUserAttempts(DBHelper.getCurrentUser());
        int overallPoints = 0;
        for (Attempt attempt : attempts) {
            overallPoints += attempt.getPoints();
        }
        OverallPoints.setText(String.valueOf(overallPoints));

    }

    private void saveQuizAttempt(int points, int correctCount, int incorrectCount) {
        AttemptDBHelper dbHelper = new AttemptDBHelper(this);
        DBHelper DBHelper= new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Get the currently logged-in user
        String currentUser = DBHelper.getCurrentUser();

        ContentValues values = new ContentValues();
        values.put(AttemptContract.AttemptEntry.COLUMN_USER, currentUser);
        values.put(AttemptContract.AttemptEntry.COLUMN_QUIZ_TYPE, quizType);
        values.put(AttemptContract.AttemptEntry.COLUMN_POINTS, points);
        values.put(AttemptContract.AttemptEntry.COLUMN_CORRECT_COUNT, correctCount);
        values.put(AttemptContract.AttemptEntry.COLUMN_INCORRECT_COUNT, incorrectCount);

        db.insert(AttemptContract.AttemptEntry.TABLE_NAME, null, values);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu - adds items to the app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.passwordChange:
                Intent intent = new Intent(this, PasswordChangeActivity.class);
                startActivity(intent);
                return true;
            case R.id.attemp:
                Intent intent_1;
                intent_1 = new Intent(this, AttemptActivity.class);
                startActivity(intent_1);
                return true;
            case R.id.logOut:
                AttemptDBHelper dbHelper = new AttemptDBHelper(this);
                List<Attempt> attempts = dbHelper.getUserAttempts(DBHelper.getCurrentUser());
                int overallPoints = 0;
                for (Attempt attempt : attempts) {
                    overallPoints += attempt.getPoints();
                }

                // Create the message with overall points
                String message = "You have overall " + overallPoints + " points.";

                // Display the message in a Toast
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                Intent intent_2;
                intent_2 = new Intent(this, LoginActivity.class);
                startActivity(intent_2);
                return true;
            case R.id.Home:
                Intent intent_3;
                intent_3 = new Intent(this, HomeActivity.class);
                startActivity(intent_3);
                return true;
            case R.id.QuizRule:
                Intent intent_4;
                intent_4 = new Intent(this, QuizRuleActivity.class);
                startActivity(intent_4);
                return true;

            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
