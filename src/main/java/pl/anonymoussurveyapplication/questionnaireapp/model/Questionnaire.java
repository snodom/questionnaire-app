package pl.anonymoussurveyapplication.questionnaireapp.model;

import javax.persistence.*;
import java.util.List;

@Table(name= "questionnaires")
@Entity
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long questionnaire_id;

    @Column
    private String questionnaire_name;

    @OneToMany
    @JoinColumn(name = "id_questionnaire", referencedColumnName="questionnaire_id")
    private List<Question> questionList;


    @OneToMany(mappedBy = "questionnaire")
    private List<AuthorizationCode> authorizationCodeList;

    public Questionnaire(){
    }

    public Questionnaire(Questionnaire questionnaire) {
        this.questionnaire_id=questionnaire.getQuestionnaire_id();
        this.questionnaire_name=questionnaire.getQuestionnaire_name();
        this.questionList=getQuestionList();
        this.authorizationCodeList=getAuthorizationCodeList();
    }

    public String getQuestionnaire_name() {
        return questionnaire_name;
    }

    public void setQuestionnaire_name(String questionnaire_name) {
        this.questionnaire_name = questionnaire_name;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Long getQuestionnaire_id() {
        return questionnaire_id;
    }

    public List<AuthorizationCode> getAuthorizationCodeList() {
        return authorizationCodeList;
    }

    public void setAuthorizationCodeList(List<AuthorizationCode> authorizationCodeList) {
        this.authorizationCodeList = authorizationCodeList;
    }
}
