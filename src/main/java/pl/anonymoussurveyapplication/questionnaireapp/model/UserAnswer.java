package pl.anonymoussurveyapplication.questionnaireapp.model;


import javax.persistence.*;

@Table(name = "user_answer")
@Entity
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long idUserAnswer;


    @Column
    private String userAnswerA;

    @Column
    private String userAnswerB;

    @Column
    private String userAnswerC;

    @Column
    private String userAnswerD;

    @Column
    private String userAnswerLong;

    @Column
    private String questio;

    @OneToOne
    private Question question;

    @OneToOne
    private AuthorizationCode authorizationCode;


    public UserAnswer() {
    }

    public UserAnswer(UserAnswer userAnswer) {
        this.authorizationCode=getAuthorizationCode();
        this.idUserAnswer = getIdUserAnswer();
        this.question = getQuestion();
        this.userAnswerA = getUserAnswerA();
        this.userAnswerB = getUserAnswerB();
        this.userAnswerC = getUserAnswerC();
        this.userAnswerD = getUserAnswerD();
        this.userAnswerLong = getUserAnswerLong();
        this.questio=getQuestio();

    }

    public String getQuestio() {
        return questio;
    }

    public void setQuestio(String questio) {
        this.questio = questio;
    }

    public String getUserAnswerA() {
        return userAnswerA;
    }

    public void setUserAnswerA(String user_answer_a) {
        this.userAnswerA = user_answer_a;
    }

    public String getUserAnswerB() {
        return userAnswerB;
    }

    public void setUserAnswerB(String user_answer_b) {
        this.userAnswerB = user_answer_b;
    }

    public String getUserAnswerC() {
        return userAnswerC;
    }

    public void setUserAnswerC(String user_answer_c) {
        this.userAnswerC = user_answer_c;
    }

    public String getUserAnswerD() {
        return userAnswerD;
    }

    public void setUserAnswerD(String user_answer_d) {
        this.userAnswerD = user_answer_d;
    }

    public String getUserAnswerLong() {
        return userAnswerLong;
    }

    public void setUserAnswerLong(String user_answer_long) {
        this.userAnswerLong = user_answer_long;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AuthorizationCode getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(AuthorizationCode authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public Long getIdUserAnswer() {
        return idUserAnswer;
    }
}
