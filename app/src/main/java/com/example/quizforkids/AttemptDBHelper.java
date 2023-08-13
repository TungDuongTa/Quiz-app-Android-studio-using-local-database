package com.example.quizforkids;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttemptDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "attempt.db";
    private static final int DATABASE_VERSION = 2;

    // Table and columns for attempts
    private static final String TABLE_ATTEMPTS = "attempts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USER_ID = "User";
    private static final String COLUMN_QUIZ_TYPE = "quizType";
    private static final String COLUMN_DATE_TIME = "date_time";
    private static final String COLUMN_POINTS = "points";
    private static final String COLUMN_CORRECT_COUNT = "correct_count";
    private static final String COLUMN_INCORRECT_COUNT = "incorrect_count";

    public AttemptDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAttemptsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTEMPTS);

        // Create a new table
        createAttemptsTable(db);
    }

    private void createAttemptsTable(SQLiteDatabase db) {
        // Create the attempts table
        String createAttemptsTableQuery = "CREATE TABLE " + TABLE_ATTEMPTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_ID + " INTEGER, " +
                COLUMN_QUIZ_TYPE + " TEXT, " +
                COLUMN_DATE_TIME + " TEXT DEFAULT (datetime('now', 'localtime')), " +
                COLUMN_POINTS + " INTEGER, " +
                COLUMN_CORRECT_COUNT + " INTEGER, " +
                COLUMN_INCORRECT_COUNT + " INTEGER)";

        db.execSQL(createAttemptsTableQuery);
    }



    @SuppressLint("Range")
    public List<Attempt> getUserAttempts(String userId) {
        List<Attempt> attempts = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_USER_ID + " = ?";
        String[] selectionArgs = { userId };

        Cursor cursor = db.query(
                TABLE_ATTEMPTS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                COLUMN_DATE_TIME + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                String quizType = cursor.getString(cursor.getColumnIndex(COLUMN_QUIZ_TYPE));

                int points = cursor.getInt(cursor.getColumnIndex(COLUMN_POINTS));
                int correctCount = cursor.getInt(cursor.getColumnIndex(COLUMN_CORRECT_COUNT));
                int incorrectCount = cursor.getInt(cursor.getColumnIndex(COLUMN_INCORRECT_COUNT));

                // Retrieve the dateTime value from the cursor as a string
                String dateTimeString = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_TIME));

                // Convert the dateTime string to a Date object
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date dateTime;
                try {
                    dateTime = dateFormat.parse(dateTimeString);
                } catch (ParseException e) {
                    e.printStackTrace();
                    dateTime = new Date(); // Set a default date if parsing fails
                }

                Attempt attempt = new Attempt(quizType, dateTime, points, correctCount, incorrectCount);
                attempts.add(attempt);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return attempts;
    }

}