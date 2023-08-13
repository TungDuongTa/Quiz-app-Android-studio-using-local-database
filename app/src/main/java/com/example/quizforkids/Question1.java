package com.example.quizforkids;


public class Question1 {
    private String question;
    private String image;
    private String answer;
    private int imageResourceId;
    public Question1() {
    }
    public Question1(String question, String image, String answer) {
        this.question = question;
        this.image = image;
        this.answer = answer;
    }


    public String getQuestion() {
        return question;
    }

    public String getImage() {
        return image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
