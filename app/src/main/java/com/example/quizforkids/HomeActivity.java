package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    CardView animal, cartoon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Home");

        animal = findViewById(R.id.animalcard);
        cartoon=findViewById(R.id.cartooncard);

        animal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AnimalActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Animal quiz", Toast.LENGTH_SHORT).show();
            }
        });
        cartoon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CartoonActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Cartoon quiz", Toast.LENGTH_SHORT).show();
            }
        });
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
}