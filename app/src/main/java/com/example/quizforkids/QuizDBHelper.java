package com.example.quizforkids;



import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizforkids.QuizContract.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QuizDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    // Table for storing quiz attempts

    //table for animal
    private static final String TABLE_ANIMAL_QUESTIONS = "AnimalQuestions";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_ANSWER = "answer";

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTIONA + " TEXT, " +
                QuestionsTable.COLUMN_OPTIONB + " TEXT, " +
                QuestionsTable.COLUMN_OPTIONC + " TEXT, " +
                QuestionsTable.COLUMN_OPTIOND + " TEXT, " +
                QuestionsTable.COLUMN_CORRECT_ANSWER + " TEXT" +
                ")";

        final String SQL_CREATE_ANIMAL_QUESTIONS_TABLE = "CREATE TABLE " +
                TABLE_ANIMAL_QUESTIONS + " ( " +
                AnimalQuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QUESTION + " TEXT, " +
                COLUMN_IMAGE + " TEXT, " +
                COLUMN_ANSWER + " TEXT" +
                ")";


        db.execSQL(SQL_CREATE_ANIMAL_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillAnimalQuestionsTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMAL_QUESTIONS);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("What is Ned Flanders’s wife’s name in the Simpsons?", "Maude", "Morde", "Xerah", "Bloom", "Maude");
        addQuestion(q1);
        Question q2 = new Question("What is the real identity of the Riddler in the Batman comics? ", "Joker", "Enigma", "Simon", "Drake", "Enigma");
        addQuestion(q2);
        Question q3 = new Question("What is an alternative name for Mickey Mouse? ", "Tiny Mouse", "Black Mouse", "Mortimer Mouse", "Fissy Mouse", "Mortimer Mouse");
        addQuestion(q3);
        Question q4 = new Question("Who is the antagonist in the “Lion King”? ", "Leo", "Scarlet", "SonG", "Scar", "Scar");
        addQuestion(q4);
        Question q5 = new Question("What strange reason was Donald Duck banned in Finland? ", "He never wore his pants", "He is rich", "He is a duck", "He lie a lot", "He never wore his pants");
        addQuestion(q5);
        Question q6 = new Question("Who is Mickey Mouse’s girlfriend?", "Minnie Mouse", "Minnyy Mouse", "Mann Mouse", "Pink Mouse", "Minnie Mouse");
        addQuestion(q6);
        Question q7 = new Question("Which cartoon character serves as the official mascot for Warner Bros Entertainment? ", "Rabbit", "Bugs Bunny", "Lion", "Dog", "Bugs Bunny");
        addQuestion(q7);
        Question q8 = new Question("What is the dog’s name in the series “Tom and Jerry”? ", "Hause", "Tommy", "Spike", "Dino", "Spike");
        addQuestion(q8);
        Question q9 = new Question("Which character’s life quest is to hunt down the Road Runner?", "Wily Dio. Coyote", "Wiliam E. Coyote", "Wily E. Coyote", "Wily E. Coyyeate", "Wily E. Coyote");
        addQuestion(q9);
        Question q10 = new Question("What is the pet’s name of the family in The Wild Thornberrys? ", "Darwin", "Harry", "Panthe", "Gumball", "Darwin");
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTIONA, question.getOptionA());
        cv.put(QuestionsTable.COLUMN_OPTIONB, question.getOptionB());
        cv.put(QuestionsTable.COLUMN_OPTIONC, question.getOptionC());
        cv.put(QuestionsTable.COLUMN_OPTIOND, question.getOptionD());
        cv.put(QuestionsTable.COLUMN_CORRECT_ANSWER, question.getCorrectAnswer());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOptionA(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTIONA)));
                question.setOptionB(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTIONB)));
                question.setOptionC(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTIONC)));
                question.setOptionD(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTIOND)));
                question.setCorrectAnswer(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_CORRECT_ANSWER)));

                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }


    private void fillAnimalQuestionsTable() {
        Question1 q1 = new Question1("What animal is this?", "animal1", "Duck");
        addAnimalQuestion(q1);
        Question1 q2 = new Question1("What animal is shown in the image?", "animal2", "Fox");
        addAnimalQuestion(q2);
        Question1 q3 = new Question1("What animal is this?", "animal3", "Elephant");
        addAnimalQuestion(q3);
        Question1 q4 = new Question1("What animal is shown in the image?", "animal4", "Crocodile");
        addAnimalQuestion(q4);
        Question1 q5 = new Question1("What animal is this?", "animal5", "Giraffe");
        addAnimalQuestion(q5);
        Question1 q6 = new Question1("What animal is shown in the image?", "animal6", "Panda");
        addAnimalQuestion(q6);
        Question1 q7 = new Question1("What animal is this?", "animal7", "Bee");
        addAnimalQuestion(q7);
        Question1 q8 = new Question1("What animal is shown in the image?", "animal8", "Frog");
        addAnimalQuestion(q8);
        Question1 q9 = new Question1("What animal is this?", "animal9", "Monkey");
        addAnimalQuestion(q9);
        Question1 q10 = new Question1("What animal is shown in the image?", "animal10", "Shark");
        addAnimalQuestion(q10);
    }




    private void addAnimalQuestion(Question1 question) {
        ContentValues cv = new ContentValues();
        cv.put(AnimalQuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(AnimalQuestionsTable.COLUMN_IMAGE, question.getImage());
        cv.put(AnimalQuestionsTable.COLUMN_ANSWER, question.getAnswer());
        db.insert(TABLE_ANIMAL_QUESTIONS, null, cv);
    }

    private int getImageResourceIdFromName(Context context, String imageName) {
        //  the image resources are stored in the "drawable" folder
        int imageResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return imageResourceId;
    }

    @SuppressLint("Range")
    public List<Question1> getAllAnimalQuestions(Context context) {
        List<Question1> questionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor animalCursor = db.rawQuery("SELECT * FROM " + TABLE_ANIMAL_QUESTIONS, null);

        if (animalCursor.moveToFirst()) {
            do {
                Question1 question = new Question1();
                question.setQuestion(animalCursor.getString(animalCursor.getColumnIndex(AnimalQuestionsTable.COLUMN_QUESTION)));
                question.setImage(animalCursor.getString(animalCursor.getColumnIndex(AnimalQuestionsTable.COLUMN_IMAGE)));
                question.setAnswer(animalCursor.getString(animalCursor.getColumnIndex(AnimalQuestionsTable.COLUMN_ANSWER)));
                String imageName = question.getImage();
                int imageResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
                question.setImageResourceId(imageResourceId);

                questionList.add(question);
            } while (animalCursor.moveToNext());
        }

        animalCursor.close();

        return questionList;
    }



}

