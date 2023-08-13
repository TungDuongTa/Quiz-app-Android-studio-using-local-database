package com.example.quizforkids;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Attempt {
    private String quizType;
    private Date dateTime;
    private int points;
    private int correctCount;
    private int incorrectCount;

    public Attempt(String quizType, Date dateTime, int points, int correctCount, int incorrectCount) {
        this.quizType = quizType;
        this.dateTime = dateTime;
        this.points = points;
        this.correctCount = correctCount;
        this.incorrectCount = incorrectCount;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public void setIncorrectCount(int incorrectCount) {
        this.incorrectCount = incorrectCount;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateFormat.format(dateTime);

        return "Quiz Type: " + quizType + "\n" +
                "Date/Time: " + formattedDateTime + "\n" +
                "Points: " + points + "\n" +
                "Correct Count: " + correctCount + "\n" +
                "Incorrect Count: " + incorrectCount;
    }
}

