package pl.anonymoussurveyapplication.questionnaireapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anonymoussurveyapplication.questionnaireapp.model.AuthorizationCode;
import pl.anonymoussurveyapplication.questionnaireapp.model.Question;
import pl.anonymoussurveyapplication.questionnaireapp.model.UserAnswer;
import pl.anonymoussurveyapplication.questionnaireapp.respository.UserAnswerRepository;
import pl.anonymoussurveyapplication.questionnaireapp.service.UserAnswerService;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    public UserAnswerRepository userAnswerRepository;

    @Override
    public void setUserAnswer(String answer, Question question, AuthorizationCode authorizationCode){

        UserAnswer userAnswer=new UserAnswer();
        userAnswer.setAuthorizationCode(authorizationCode);
        userAnswer.setQuestion(question);
        userAnswer.setQuestio(question.getTitleQuestion());

       if(answer.equals("A") || answer.equals("a") ){
           userAnswer.setUserAnswerA(answer);
       }
       else if(answer.equals("B") || answer.equals("b")) {
           userAnswer.setUserAnswerB(answer);
        }
       else if(answer.equals("C") || answer.equals("c")) {
           userAnswer.setUserAnswerC(answer);
       }
       else if(answer.equals("D") || answer.equals("d") ) {
           userAnswer.setUserAnswerD(answer);
       }
       else userAnswer.setUserAnswerLong(answer);
        userAnswerRepository.save(userAnswer);
    }

    @Override
    public List<UserAnswer> getAllAnswersByQuestionnaireIdAndAuthorizationCodeId(Long questionnaireId, Long authorizationCodeId) {
        return userAnswerRepository.findAllByQuestionQuestionnaireQuestionnaireIdAndAuthorizationCode_IdAuthorizationCode(questionnaireId, authorizationCodeId);
    }

    @Override
    public List<UserAnswer> findUserAnswersByAuthorizationCode_IdAuthorizationCode(Long authorizationCodeId) {
        List <UserAnswer> userAnswerList;
        userAnswerList = userAnswerRepository.findUserAnswersByAuthorizationCode_IdAuthorizationCode(authorizationCodeId);

        userAnswerList.forEach(userAnswer -> {
            userAnswer.setQuestion(null);
            userAnswer.setAuthorizationCode(null);
        });
        return userAnswerList;
    }

    @Override
    public UserAnswer getOneAnswerByQuestionId(Long questionId, Long authorizationCodeId) {
        return userAnswerRepository.findByAuthorizationCode_IdAuthorizationCodeAndQuestionQuestionId(questionId, authorizationCodeId);
    }
}
