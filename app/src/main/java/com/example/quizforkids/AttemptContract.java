package com.example.quizforkids;

import android.provider.BaseColumns;

public final class AttemptContract {
    private AttemptContract() {}

    public static class AttemptEntry implements BaseColumns {
        public static final String TABLE_NAME = "attempts";
        public static final String COLUMN_POINTS = "points";
        public static final String COLUMN_CORRECT_COUNT = "correct_count";
        public static final String COLUMN_INCORRECT_COUNT = "incorrect_count";
        public static final String COLUMN_QUIZ_TYPE = "quizType";
        public static final String COLUMN_DATE_TIME = "dateTime";
        public static final String COLUMN_USER = "User";

        public static final String COLUMN_USERNAME = "Username";

    }
}
