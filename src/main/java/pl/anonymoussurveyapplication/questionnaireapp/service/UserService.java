package pl.anonymoussurveyapplication.questionnaireapp.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean checkUserData(String login, String password);
}
