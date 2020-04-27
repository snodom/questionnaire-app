package pl.anonymoussurveyapplication.questionnaireapp.model;


import javax.persistence.*;

@Table(name= "question")
@Entity
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long questionId;

    @Column
    private String titleQuestion;

    @Column
    private String answerA;

    @Column
    private String answerB;

    @Column
    private String answerC;

    @Column
    private String answerD;

    @Column
    private String answerLong;

    @ManyToOne
    @JoinColumn(name="questionnaire_id")
    private Questionnaire questionnaire;


    public Question() {
    }

    public Question(Question question) {
        this.questionId = getQuestionId();
        this.titleQuestion = getTitleQuestion();
        this.answerA = getAnswerA();
        this.answerB = getAnswerB();
        this.answerC = getAnswerC();
        this.answerD = getAnswerD();
        this.answerLong = getAnswerLong();
        this.questionnaire=getQuestionnaire();
    }


    public String getTitleQuestion() {
        return titleQuestion;
    }

    public void setTitleQuestion(String title_question) {
        this.titleQuestion = getTitleQuestion();
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answer_a) {
        this.answerA = getAnswerA();
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answer_b) {
        this.answerB = getAnswerB();
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answer_c) {
        this.answerC = getAnswerC();
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answer_d) {
        this.answerD = getAnswerD();
    }

    public String getAnswerLong() {
        return answerLong;
    }

    public void setAnswerLong(String answer_long) {
        this.answerLong = getAnswerLong();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
}
