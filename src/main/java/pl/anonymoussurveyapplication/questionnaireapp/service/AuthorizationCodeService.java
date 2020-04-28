package pl.anonymoussurveyapplication.questionnaireapp.service;

import pl.anonymoussurveyapplication.questionnaireapp.model.AuthorizationCode;
import pl.anonymoussurveyapplication.questionnaireapp.model.Questionnaire;

import java.util.List;

public interface AuthorizationCodeService {
    List<AuthorizationCode> getAll();
    void createCodeForQuestionnaire(Questionnaire questionnaire);
    void createQuantityOfAuthorizationCodes(Questionnaire questionnaire, int quantityCodes);
    void deleteAuthorizationCode(Long authorizationCode_id);
    void used(Long authorizationCode_id);
}
