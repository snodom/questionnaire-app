package pl.anonymoussurveyapplication.questionnaireapp.service;

import pl.anonymoussurveyapplication.questionnaireapp.model.Question;
import pl.anonymoussurveyapplication.questionnaireapp.model.UserAnswer;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserAnswerService {

    void setUserAnswer(String answer, Question question, Long authorizationCode);
    List<UserAnswer> getAllAnswersByQuestionnaireIdAndAuthorizationCodeId(Long questionnaireId, Long authorizationCodeId);
    List<UserAnswer> findUserAnswersByAuthorizationCode_IdAuthorizationCode(Long authorizationCodeId) throws NoSuchAlgorithmException;
    UserAnswer getOneAnswerByQuestionId(Long questionId, Long authorizationCodeId);

}
