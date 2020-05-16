package pl.anonymoussurveyapplication.questionnaireapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name= "authorization_code")
@Entity
public class AuthorizationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long idAuthorizationCode;

    @Column
    @NotNull
    private Long authorizationCode;

    @Column
    @NotNull
    private Boolean used;


    @ManyToOne
    @JoinColumn(name="questionnaire_id")
    private Questionnaire questionnaire;

    @Column
    private String encryptedUserResponses;

    public AuthorizationCode() {
    }

    public AuthorizationCode(AuthorizationCode authorizationCode) {
        this.authorizationCode = getAuthorizationCode();
        this.idAuthorizationCode = getIdAuthorizationCode();
        this.used=getUsed();
        this.questionnaire =getQuestionnaire();
        this.encryptedUserResponses = getEncryptedUserResponses();
    }

    public Long getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(Long authorization_code) {
        this.authorizationCode = authorization_code;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Long getIdAuthorizationCode() {
        return idAuthorizationCode;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public void setIdAuthorizationCode(Long idAuthorizationCode) {
        this.idAuthorizationCode = idAuthorizationCode;
    }

    public String getEncryptedUserResponses() {
        return encryptedUserResponses;
    }

    public void setEncryptedUserResponses(String encryptedUserResponses) {
        this.encryptedUserResponses = encryptedUserResponses;
    }
    public void addLinetoEncryptedUserResponses(String line){
        this.encryptedUserResponses=getEncryptedUserResponses()+line;
    }
}
