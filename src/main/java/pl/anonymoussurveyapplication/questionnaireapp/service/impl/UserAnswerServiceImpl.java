package pl.anonymoussurveyapplication.questionnaireapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anonymoussurveyapplication.questionnaireapp.model.AuthorizationCode;
import pl.anonymoussurveyapplication.questionnaireapp.model.Question;
import pl.anonymoussurveyapplication.questionnaireapp.model.UserAnswer;
import pl.anonymoussurveyapplication.questionnaireapp.respository.AuthorizationCodeRepository;
import pl.anonymoussurveyapplication.questionnaireapp.respository.UserAnswerRepository;
import pl.anonymoussurveyapplication.questionnaireapp.service.AuthorizationCodeService;
import pl.anonymoussurveyapplication.questionnaireapp.service.UserAnswerService;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    public UserAnswerRepository userAnswerRepository;

    @Autowired
    public AuthorizationCodeService authorizationCodeService;

    @Autowired
    public AuthorizationCodeRepository authorizationCodeRepository;

    @Override
    public void setUserAnswer(String answer, Question question, Long authorizationCode){

        if(!authorizationCodeService.getAuthorizationCodeByAuthorizationCode(authorizationCode).getUsed()){
            authorizationCodeService.used(authorizationCode);
        }


        UserAnswer userAnswer=new UserAnswer();
        userAnswer.setAuthorizationCode(authorizationCodeService.getAuthorizationCodeByAuthorizationCode(authorizationCode));
        userAnswer.setQuestion(question);
        userAnswer.setQuestio(question.getTitleQuestion());

         userAnswer.setUserAnswerLong(answer);

        userAnswerRepository.save(userAnswer);
    }

    @Override
    public List<UserAnswer> getAllAnswersByQuestionnaireIdAndAuthorizationCodeId(Long questionnaireId, Long authorizationCodeId) {
        return userAnswerRepository.findAllByQuestionQuestionnaireQuestionnaireIdAndAuthorizationCode_IdAuthorizationCode(questionnaireId, authorizationCodeId);
    }

    @Override
    public List<UserAnswer> findUserAnswersByAuthorizationCode_IdAuthorizationCode(Long authorizationCodeId) throws NoSuchAlgorithmException {
        List <UserAnswer> userAnswerList;
        userAnswerList = userAnswerRepository.findUserAnswersByAuthorizationCode_IdAuthorizationCode(authorizationCodeId);
        AuthorizationCode authorizationCode = authorizationCodeRepository.findByIdAuthorizationCode(authorizationCodeId);
        authorizationCode.setEncryptedUserResponses("");
        authorizationCode.addLinetoEncryptedUserResponses(authorizationCode.getAuthorizationCode().toString());
        userAnswerList.forEach(userAnswer -> {
            authorizationCode.addLinetoEncryptedUserResponses(userAnswer.getQuestion().getQuestionId()+userAnswer.getUserAnswerLong());
        });

        System.out.println(authorizationCode.getEncryptedUserResponses());
        authorizationCode.setEncryptedUserResponses(toHexString(getSHA(authorizationCode.getEncryptedUserResponses()+"sol")));

        authorizationCodeRepository.save(authorizationCode);

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


    // funkcja szyfrujaca sha-256
    private byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
