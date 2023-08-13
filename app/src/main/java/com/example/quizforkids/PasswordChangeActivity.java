package com.example.quizforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class PasswordChangeActivity extends AppCompatActivity {
    EditText oldPass, newPass;
    Button confirmButton;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        setTitle("Change Password");

        dbHelper = new DBHelper(this);

        oldPass = findViewById(R.id.currentPass);
        newPass = findViewById(R.id.newPass);
        confirmButton = findViewById(R.id.confirmbtn);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPassword = oldPass.getText().toString().trim();
                String newPassword = newPass.getText().toString().trim();
                String loggedInUsername = dbHelper.getCurrentUser();

                if (!validateInput(currentPassword, newPassword)) {
                    return;
                }

                if (loggedInUsername != null) {
                    // Check if the current password matches the password in the database
                    if (dbHelper.checkusernamepassword(loggedInUsername, currentPassword)) {
                        // Update the password in the database
                        if (dbHelper.updatePassword(loggedInUsername, newPassword)) {
                            Toast.makeText(PasswordChangeActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PasswordChangeActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(PasswordChangeActivity.this, "Failed to change password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PasswordChangeActivity.this, "Incorrect current password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PasswordChangeActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInput(String currentPassword, String newPassword) {
        if (currentPassword.isEmpty()) {
            oldPass.setError("Enter your current password");
            return false;
        }

        if (newPassword.isEmpty()) {
            newPass.setError("Enter a new password");
            return false;
        }

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

            default:
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}


