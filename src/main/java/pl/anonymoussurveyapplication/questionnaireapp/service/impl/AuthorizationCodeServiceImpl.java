package pl.anonymoussurveyapplication.questionnaireapp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anonymoussurveyapplication.questionnaireapp.model.AuthorizationCode;
import pl.anonymoussurveyapplication.questionnaireapp.model.Questionnaire;
import pl.anonymoussurveyapplication.questionnaireapp.respository.AuthorizationCodeRepository;
import pl.anonymoussurveyapplication.questionnaireapp.respository.QuestionnaireRepository;
import pl.anonymoussurveyapplication.questionnaireapp.service.AuthorizationCodeService;

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
        return authorizationCodeRepository.findAllByQuestionnaireQuestionnaireId(questionnaireId);
    }

    @Override
    public AuthorizationCode getLastCodForQuestionnaireId(Long questionnaireId) {
        List<AuthorizationCode> authorizationCodes;
        authorizationCodes=authorizationCodeRepository.findAllByQuestionnaireQuestionnaireId(questionnaireId);
        return authorizationCodes.get(authorizationCodes.size()-1);
    }

    @Override
    public void createCodeForQuestionnaire(Questionnaire questionnaire) {

        AuthorizationCode authorizationCode = new AuthorizationCode();
        authorizationCode.setAuthorizationCode(TokenServiceImpl.RandomUniqueCodeGenerator(1).toString());
        authorizationCode.setUsed(false);

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
    public void used(Long authorizationCodeId) {
        authorizationCodeRepository.getOne(authorizationCodeId).setUsed(true);
    }
}
