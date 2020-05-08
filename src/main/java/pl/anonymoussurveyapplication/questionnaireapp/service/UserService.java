package pl.anonymoussurveyapplication.questionnaireapp.service;

import org.springframework.stereotype.Service;

public interface UserService {
    boolean checkUserData(String login, String password);
}
