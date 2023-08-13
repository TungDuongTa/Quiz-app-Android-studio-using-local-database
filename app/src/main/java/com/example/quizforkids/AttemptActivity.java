package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AttemptActivity extends AppCompatActivity {
    private AttemptDBHelper dbHelper;
    private DBHelper DBHelper;
    private ListView listViewAttempts;
    TextView OverallPoint;
    Button DateSort, TypeSort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt);
        setTitle("Attempt history");

        DateSort=findViewById(R.id.dateSort);
        TypeSort=findViewById(R.id.typeSort);
        DBHelper = new DBHelper(this);
        listViewAttempts = findViewById(R.id.listViewAttempts);
        dbHelper = new AttemptDBHelper(this);

        // Get the currently logged-in user
        String currentUser = DBHelper.getCurrentUser();

        // Retrieve the attempts for the current user from the database
        List<Attempt> attempts = dbHelper.getUserAttempts(currentUser);

        // Calculate the overall points for the current user
        int overallPoints = 0;
        for (Attempt attempt : attempts) {
            overallPoints += attempt.getPoints();
        }

        // Set the appropriate message in the TextView
        OverallPoint = findViewById(R.id.textView14);
        OverallPoint.setText(String.valueOf(overallPoints));

        // Create an ArrayAdapter to display the attempts in the ListView
        ArrayAdapter<Attempt> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, attempts);

        // Set the adapter for the ListView
        listViewAttempts.setAdapter(adapter);

        DateSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort attempts by dateTime
                Collections.sort(attempts, new DateTimeComparator());
                adapter.notifyDataSetChanged();
            }
        });

        TypeSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sort attempts by quizType
                Collections.sort(attempts, new QuizTypeComparator());
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
                DBHelper DBHelper= new DBHelper(this);
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
    // Comparator class to compare attempts based on dateTime
    private class DateTimeComparator implements Comparator<Attempt> {
        @Override
        public int compare(Attempt attempt1, Attempt attempt2) {
            return attempt1.getDateTime().compareTo(attempt2.getDateTime());
        }
    }

    // Comparator class to compare attempts based on quizType
    private class QuizTypeComparator implements Comparator<Attempt> {
        @Override
        public int compare(Attempt attempt1, Attempt attempt2) {
            return attempt1.getQuizType().compareTo(attempt2.getQuizType());
        }
    }

}