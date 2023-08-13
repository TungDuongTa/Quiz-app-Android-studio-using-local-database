package com.example.quizforkids;
import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {
    }

    public static class QuestionsTable implements BaseColumns  {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTIONA = "optionA";
        public static final String COLUMN_OPTIONB = "optionB";
        public static final String COLUMN_OPTIONC = "optionC";
        public static final String COLUMN_OPTIOND = "optionD";
        public static final String COLUMN_CORRECT_ANSWER = "CorrectAnswer";
    }


    // Define the animal questions table
    public static class AnimalQuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "animal_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_ANSWER = "answer";
        public static final String COLUMN_IMAGE_RESOURCE_ID = "image_resource_id";
    }

}
