package pl.anonymoussurveyapplication.questionnaireapp.service;

import pl.anonymoussurveyapplication.questionnaireapp.model.AuthorizationCode;

import java.util.List;

public interface AuthorizationCodeService {
    List<AuthorizationCode> getAll();
    void createCodeForQuestionnaire(Long questionnaire_id);
    void createQuantityOfAuthorizationCodes(Long questionnaire_id, int quantityCodes);
    void deleteAuthorizationCode(Long authorizationCode_id);
    AuthorizationCode used(Long authorizationCode_id);
}
