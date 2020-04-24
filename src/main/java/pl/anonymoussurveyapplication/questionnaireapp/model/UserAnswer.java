package pl.anonymoussurveyapplication.questionnaireapp.model;


import javax.persistence.*;

@Table(name = "user_answer")
@Entity
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id_user_answer;


    @Column
    private String user_answer_a;

    @Column
    private String user_answer_b;

    @Column
    private String user_answer_c;

    @Column
    private String user_answer_d;

    @Column
    private String user_answer_long;

    @OneToOne
    private Question question;

    @OneToOne
    private AuthorizationCode authorizationCode;

    public UserAnswer() {
    }

    public UserAnswer(UserAnswer userAnswer) {
        this.authorizationCode=getAuthorizationCode();
        this.id_user_answer=getId_user_answer();
        this.question=getQuestion();
        this.user_answer_a=getUser_answer_a();
        this.user_answer_b=getUser_answer_b();
        this.user_answer_c=getUser_answer_c();
        this.user_answer_d=getUser_answer_d();
        this.user_answer_long=getUser_answer_long();
    }

    public String getUser_answer_a() {
        return user_answer_a;
    }

    public void setUser_answer_a(String user_answer_a) {
        this.user_answer_a = user_answer_a;
    }

    public String getUser_answer_b() {
        return user_answer_b;
    }

    public void setUser_answer_b(String user_answer_b) {
        this.user_answer_b = user_answer_b;
    }

    public String getUser_answer_c() {
        return user_answer_c;
    }

    public void setUser_answer_c(String user_answer_c) {
        this.user_answer_c = user_answer_c;
    }

    public String getUser_answer_d() {
        return user_answer_d;
    }

    public void setUser_answer_d(String user_answer_d) {
        this.user_answer_d = user_answer_d;
    }

    public String getUser_answer_long() {
        return user_answer_long;
    }

    public void setUser_answer_long(String user_answer_long) {
        this.user_answer_long = user_answer_long;
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

    public Long getId_user_answer() {
        return id_user_answer;
    }
}
