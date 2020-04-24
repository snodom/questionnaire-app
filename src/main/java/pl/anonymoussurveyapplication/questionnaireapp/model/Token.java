package pl.anonymoussurveyapplication.questionnaireapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name= "token")
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long token_id;

    @Column
    @NotNull
    private String token_code;

    @Column
    @NotNull
    private Boolean used;

    public Token() {
    }

    public String getToken_code() {
        return token_code;
    }

    public void setToken_code(String token_code) {
        this.token_code = token_code;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Long getToken_id() {
        return token_id;
    }

    public void setToken_id(Long token_id) {
        this.token_id = token_id;
    }

    public Token(Token token) {
        this.token_code=token.getToken_code();
        this.token_id=token.getToken_id();
        this.used=token.getUsed();
    }
}
