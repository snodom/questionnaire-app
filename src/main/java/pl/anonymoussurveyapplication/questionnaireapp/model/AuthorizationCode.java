package pl.anonymoussurveyapplication.questionnaireapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name= "authorization_code")
@Entity
public class AuthorizationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id_authorization_code;

    @Column
    @NotNull
    private String authorization_code;

    @Column
    @NotNull
    private Boolean used;

    @ManyToOne
    @JoinColumn(name="questionnaire_id")
    private Questionnaire questionnaire;

    public AuthorizationCode() {
    }

    public AuthorizationCode(AuthorizationCode authorizationCode) {
        this.authorization_code=getAuthorization_code();
        this.id_authorization_code=getId_authorization_code();
        this.used=getUsed();
        this.questionnaire =getQuestionnaire();
    }

    public String getAuthorization_code() {
        return authorization_code;
    }

    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Long getId_authorization_code() {
        return id_authorization_code;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
}
