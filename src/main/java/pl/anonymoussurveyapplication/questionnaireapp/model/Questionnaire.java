package pl.anonymoussurveyapplication.questionnaireapp.model;

import javax.persistence.*;
import java.util.List;

@Table(name= "questionnaire")
@Entity
public class  Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long questionnaireId;

    @Column
    private String questionnaireName;

    @OneToMany(mappedBy = "questionnaire")
    private List<Question> questionList;


    @OneToMany(mappedBy = "questionnaire",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<AuthorizationCode> authorizationCodeList;

    public Questionnaire(){
    }

    public Questionnaire(Questionnaire questionnaire) {
        this.questionnaireId =questionnaire.getQuestionnaireId();
        this.questionnaireName =questionnaire.getQuestionnaireName();
        this.questionList=getQuestionList();
        this.authorizationCodeList=getAuthorizationCodeList();
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaire_name) {
        this.questionnaireName = questionnaire_name;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public List<AuthorizationCode> getAuthorizationCodeList() {
        return authorizationCodeList;
    }

    public void setAuthorizationCodeList(List<AuthorizationCode> authorizationCodeList) {
        this.authorizationCodeList = authorizationCodeList;
    }
}
