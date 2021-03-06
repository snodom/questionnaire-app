package pl.anonymoussurveyapplication.questionnaireapp.service.impl;


import javafx.print.Collation;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anonymoussurveyapplication.questionnaireapp.model.AuthorizationCode;
import pl.anonymoussurveyapplication.questionnaireapp.model.Questionnaire;
import pl.anonymoussurveyapplication.questionnaireapp.respository.AuthorizationCodeRepository;
import pl.anonymoussurveyapplication.questionnaireapp.respository.QuestionnaireRepository;
import pl.anonymoussurveyapplication.questionnaireapp.service.AuthorizationCodeService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class AuthorizationCodeServiceImpl implements AuthorizationCodeService {

    @Autowired
    public AuthorizationCodeRepository authorizationCodeRepository;

    @Autowired
    public QuestionnaireRepository questionnaireRepository;


    @Override
    public List<AuthorizationCode> getAllByQuestionnaireId(Long questionnaireId) {
        List<AuthorizationCode> authorizationCodes = authorizationCodeRepository.findAuthorizationCodesByQuestionnaireQuestionnaireId(questionnaireId);
        authorizationCodes.forEach(authorizationCode -> {
            authorizationCode.setQuestionnaire(null);
        });
        return authorizationCodes;
    }


    @Override
    public AuthorizationCode getLastCodForQuestionnaireId(Long questionnaireId) {
        List<AuthorizationCode> authorizationCodes;
        authorizationCodes=authorizationCodeRepository.findAuthorizationCodesByQuestionnaireQuestionnaireId(questionnaireId);
        authorizationCodes.sort(Comparator.comparing(AuthorizationCode::getIdAuthorizationCode));

            authorizationCodes.forEach(authorizationCode -> {
                System.out.println(authorizationCode.getIdAuthorizationCode());
            });
            

        return authorizationCodes.get(authorizationCodes.size()-1);
    }

    @Override
    public void createCodeForQuestionnaire(Questionnaire questionnaire) {

        AuthorizationCode authorizationCode = new AuthorizationCode();

        authorizationCode.setAuthorizationCode((long) TokenServiceImpl.RandomUniqueCodeGenerator(1).get(0));
        authorizationCode.setUsed(false);
        authorizationCode.setQuestionnaire(questionnaire);

        questionnaire.getAuthorizationCodeList().add(authorizationCode);

        authorizationCodeRepository.save(authorizationCode);
        questionnaireRepository.save(questionnaire);

    }

    @Override
    public void createQuantityOfAuthorizationCodes(Questionnaire questionnaire, int quantityCodes) {

        IntStream.rangeClosed(0, quantityCodes).mapToObj(i -> questionnaire).forEach(this::createCodeForQuestionnaire);
    }

    @Override
    public void deleteAuthorizationCode(Long authorizationCodeId) {
        authorizationCodeRepository.deleteById(authorizationCodeId);
    }

    @Override
    public void used(Long authorizationCode) {
        AuthorizationCode authorizationCode1 = authorizationCodeRepository.findByAuthorizationCode(authorizationCode);
        authorizationCode1.setUsed(true);
        authorizationCodeRepository.save(authorizationCode1);
    }

    @Override
    public AuthorizationCode getAuthorizationCodeByAuthorizationCode(Long authorizationCode) {
        return authorizationCodeRepository.findByAuthorizationCode(authorizationCode);
    }
}
