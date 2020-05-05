package pl.anonymoussurveyapplication.questionnaireapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name= "token")
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long tokenId;

    @Column
    @NotNull
    private Long tokenCode;

    @Column
    @NotNull
    private Boolean used;

    @Column
    @NotNull
    private Long questionnaireId;



    public Token() {
    }

    public Long getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(Long token_code) {
        this.tokenCode = token_code;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Token(Token token) {
        this.tokenCode =token.getTokenCode();
        this.tokenId =token.getTokenId();
        this.used=token.getUsed();
        this.questionnaireId=token.getQuestionnaireId();
    }
}
