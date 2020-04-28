package pl.anonymoussurveyapplication.questionnaireapp.service;

import pl.anonymoussurveyapplication.questionnaireapp.model.AuthorizationCode;
import pl.anonymoussurveyapplication.questionnaireapp.model.Question;
import pl.anonymoussurveyapplication.questionnaireapp.model.UserAnswer;

import java.util.List;

public interface UserAnswerService {

    void setUserAnswer(String answer, Question question, AuthorizationCode authorizationCode);
    List<UserAnswer> getAllAnswersByQuestionnaireIdAndAuthorizationCodeId(Long questionnaireId, Long authorizationCodeId);
    UserAnswer getOneAnswerByQuestionId(Long questionId, Long authorizationCodeId);

}
