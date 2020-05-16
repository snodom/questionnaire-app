package pl.anonymoussurveyapplication.questionnaireapp.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.anonymoussurveyapplication.questionnaireapp.model.User;
import pl.anonymoussurveyapplication.questionnaireapp.respository.UserRepository;
import pl.anonymoussurveyapplication.questionnaireapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean checkUserData(String data) {
        Gson g =new Gson();
        User user = g.fromJson(data.trim(),User.class);

        return userRepository.findAllByLogin(user.getLogin()).getPassword().equals(user.getPassword());
    }
}
