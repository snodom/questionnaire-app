package pl.anonymoussurveyapplication.questionnaireapp.modelDto;

public class QuestionDto {
    private String titleQuestion;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answerLong;

    public String getTitleQuestion() {
        return titleQuestion;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getAnswerLong() {
        return answerLong;
    }

    public void setTitleQuestion(String titleQuestion) {
        this.titleQuestion = titleQuestion;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public void setAnswerLong(String answerLong) {
        this.answerLong = answerLong;
    }
}
