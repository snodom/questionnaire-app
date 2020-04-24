package pl.anonymoussurveyapplication.questionnaireapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name= "question")
@Entity
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long question_id;

    @Column
    private String title_question;

    @Column
    private String answer_a;

    @Column
    private String answer_b;

    @Column
    private String answer_c;

    @Column
    private String answer_d;

    @Column
    private String answer_long;


    public Question() {
    }

    public Question(Question question) {
        this.question_id=getQuestion_id();
        this.title_question=getTitle_question();
        this.answer_a=getAnswer_a();
        this.answer_b=getAnswer_b();
        this.answer_c=getAnswer_c();
        this.answer_d=getAnswer_d();
        this.answer_long=getAnswer_long();
    }


    public String getTitle_question() {
        return title_question;
    }

    public void setTitle_question(String title_question) {
        this.title_question = getTitle_question();
    }

    public String getAnswer_a() {
        return answer_a;
    }

    public void setAnswer_a(String answer_a) {
        this.answer_a = getAnswer_a();
    }

    public String getAnswer_b() {
        return answer_b;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = getAnswer_b();
    }

    public String getAnswer_c() {
        return answer_c;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = getAnswer_c();
    }

    public String getAnswer_d() {
        return answer_d;
    }

    public void setAnswer_d(String answer_d) {
        this.answer_d = getAnswer_d();
    }

    public String getAnswer_long() {
        return answer_long;
    }

    public void setAnswer_long(String answer_long) {
        this.answer_long = getAnswer_long();
    }

    public Long getQuestion_id() {
        return question_id;
    }
}
