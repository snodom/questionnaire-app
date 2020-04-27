package pl.anonymoussurveyapplication.questionnaireapp.service;

import pl.anonymoussurveyapplication.questionnaireapp.model.Token;

import java.util.List;

public interface TokenService {
    List<Token> getAllForQuestionnaire(Long questionnaireId);
    void createTokenForCodeGenerator(Long questionnaireId);
    void createQuantityOfTokensForCodeGenerator(Long questionnaireId, int quantityCodes);
    void deleteToken(Long tokenId);
    Token used(Long tokenId);
}
