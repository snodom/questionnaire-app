package pl.anonymoussurveyapplication.questionnaireapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anonymoussurveyapplication.questionnaireapp.model.Token;
import pl.anonymoussurveyapplication.questionnaireapp.respository.TokenRespository;
import pl.anonymoussurveyapplication.questionnaireapp.service.TokenService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TokenServiceImpl implements TokenService {


    // zablokować liczbę większą od 10000
    public List<Integer> RandomUniqueCodeGenerator(int quantity){

        List<Integer> randomUniqueCodeList = new ArrayList<>(quantity);
        ThreadLocalRandom.current().ints(90000, 100000).distinct().limit(quantity).forEach(randomUniqueCodeList::add);

        return randomUniqueCodeList;
    }

    @Autowired
    private TokenRespository tokenRespository;

    @Override
    public List<Token> getAllForQuestionnaire(Long questionnaireId) {
        return tokenRespository.findAll();
    }
/*
// TODO przeniesc do AUTHORIZATION CODE SERVICE IMPL

    @Override
    public void createTokenForQuestionnaire(Long questionnaireId) {
        Token token = new Token();
        token.setTokenCode(RandomUniqueCodeGenerator(1).toString());
        token.setUsed(false);
        token.setQuestionnaireId(questionnaireId);

        tokenRespository.save(token);
    }
*/
    @Override
    public void createTokenForCodeGenerator(Long questionnaireId) {
        Token token = new Token();
        token.setTokenCode(RandomUniqueCodeGenerator(1).toString());
        token.setUsed(false);
        token.setQuestionnaireId(questionnaireId);

        tokenRespository.save(token);
    }


    @Override
    public void createQuantityOfTokensForCodeGenerator(Long questionnaireId, int quantityCodes) {

    }

    @Override
    public void deleteToken(Long tokenId) {

    }

    @Override
    public Token used(Long tokenId) {
        return null;
    }
}
