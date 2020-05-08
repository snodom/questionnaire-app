package pl.anonymoussurveyapplication.questionnaireapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name= "user")
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long userId;

    @Column
    @NotNull
    private String login;

    @Column
    @NotNull
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public User(){}


    public User(User user){
        this.userId= getUserId();
        this.login =  getLogin();
        this.password = getPassword();
    }
}
