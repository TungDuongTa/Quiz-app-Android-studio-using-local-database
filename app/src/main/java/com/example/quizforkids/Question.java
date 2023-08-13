package com.example.quizforkids;

public class Question {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String  CorrectAnswer;
    private boolean answered;
    public Question(){


    }
    public Question(String question, String optionA, String optionB, String optionC, String optionD, String  CorrectAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.CorrectAnswer = CorrectAnswer;
    }


    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {

        this.question = question;
    }

    public String getOptionA() {

        return optionA;
    }

    public void setOptionA(String optionA) {

        this.optionA = optionA;
    }

    public String getOptionB() {

        return optionB;
    }

    public void setOptionB(String optionB) {

        this.optionB = optionB;
    }

    public String getOptionC() {

        return optionC;
    }

    public void setOptionC(String optionC) {

        this.optionC = optionC;
    }
    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {

        this.optionD = optionD;
    }

    public String  getCorrectAnswer() {

        return CorrectAnswer;
    }

    public void setCorrectAnswer(String  CorrectAnswer) {

        this.CorrectAnswer = CorrectAnswer;
    }
    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }


}