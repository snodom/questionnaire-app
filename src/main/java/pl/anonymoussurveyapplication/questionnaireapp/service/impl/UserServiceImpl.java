package pl.anonymoussurveyapplication.questionnaireapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anonymoussurveyapplication.questionnaireapp.respository.UserRepository;
import pl.anonymoussurveyapplication.questionnaireapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean checkUserData(String login, String password) {
        return userRepository.findAllByLogin(login).getPassword().equals(password);
    }
}
