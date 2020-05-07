package pl.anonymoussurveyapplication.questionnaireapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anonymoussurveyapplication.questionnaireapp.model.Token;
import pl.anonymoussurveyapplication.questionnaireapp.respository.TokenRespository;
import pl.anonymoussurveyapplication.questionnaireapp.service.TokenService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
public class TokenServiceImpl implements TokenService {

//TODO ZMIENIC Z STRINGA KONDY NA INT
    // zablokować liczbę większą od 10000
    static List<Integer> RandomUniqueCodeGenerator(int quantity){

        List<Integer> randomUniqueCodeList = new ArrayList<>(quantity);
        ThreadLocalRandom.current().ints(90000, 1000000).distinct().limit(quantity).forEach(randomUniqueCodeList::add);

        return randomUniqueCodeList;
    }

    @Autowired
    private TokenRespository tokenRespository;

    @Override
    public List<Token> getAllForQuestionnaire(Long questionnaireId) {
        return tokenRespository.findAll();
    }

    @Override
    public Long getQuestionnaireIdByTokenCode(Long tokenCode) {

        Token token = tokenRespository.findByTokenCode(tokenCode);
        return token.getQuestionnaireId();
    }


    @Override
    public void createTokenForCodeGenerator(Long questionnaireId) {

            Token token = new Token();
            token.setTokenCode((long) TokenServiceImpl.RandomUniqueCodeGenerator(1).get(0));
            token.setUsed(false);
            token.setQuestionnaireId(questionnaireId);

            tokenRespository.save(token);
    }


    @Override
    public void createQuantityOfTokensForCodeGenerator(Long questionnaireId, int quantityCodes) {

        IntStream.rangeClosed(0, quantityCodes).mapToObj(i -> questionnaireId).forEach(this::createTokenForCodeGenerator);

    }

    @Override
    public void deleteToken(Long tokenId) {
        tokenRespository.deleteById(tokenId);
    }

    @Override
    public void used(Long tokenId) {
        Token token = tokenRespository.findByTokenCode(tokenId);
        token.setUsed(true);
        tokenRespository.save(token);
    }

    @Override
    public boolean checkused(Long tokenId) {
        tokenRespository.findByTokenCode(tokenId);
        return !tokenRespository.findByTokenCode(tokenId).getUsed();
    }
}
