package pl.anonymoussurveyapplication.questionnaireapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.anonymoussurveyapplication.questionnaireapp.model.*;
import pl.anonymoussurveyapplication.questionnaireapp.service.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorizationCodeService authorizationCodeService;

    @Autowired
    private UserAnswerService userAnswerService;

    /*
 ____  ____   _____    ___ ______    ___  _____ ______   ___   __    __   ____  ____     ___
|    \|    \ |     |  /  _]      |  /  _]/ ___/|      | /   \ |  |__|  | /    ||    \   /  _]
|  o  )  D  )|__/  | /  [_|      | /  [_(   \_ |      ||     ||  |  |  ||  o  ||  _  | /  [_
|   _/|    / |   __||    _]_|  |_||    _]\__  ||_|  |_||  O  ||  |  |  ||     ||  |  ||    _]
|  |  |    \ |  /  ||   [_  |  |  |   [_ /  \ |  |  |  |     ||  `  '  ||  _  ||  |  ||   [_
|  |  |  .  \|     ||     | |  |  |     |\    |  |  |  |     | \      / |  |  ||  |  ||     |
|__|  |__|\_||_____||_____| |__|  |_____| \___|  |__|   \___/   \_/\_/  |__|__||__|__||_____|
     */

    @PostMapping("/userAnswer/addUserAnswer")
    public ResponseEntity<String> addUserAnswerForQuestion(@Valid @RequestBody @RequestParam("answer") String answer, @RequestParam("questionId") Long questionId, @RequestParam("authorizationCode")Long authorizationCode){
        try{
            userAnswerService.setUserAnswer(answer,questionService.getQuestionById(questionId),authorizationCodeService.getAuthorizationCodeByAuthorizationCode(authorizationCode));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nie udalo sie dodać odpowiedzi do pytania, bad request");
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    /*
        {
"titleQuestion" : "title",
"answerA": " PYTANIE a",
"answerB": " PYTANIE a",
"answerC": " PYTANIE a",
"answerD": " PYTANIE a",
"answerLong": " PYTANIE a",

"questionnaire":{
		"questionnaireId": 1
	}
}

     */
//tworzenie pytania dla ankiety
    @PostMapping("/question/createQuestionForQuestionnaire")
    public ResponseEntity<String> createQuestionForQuestionnaire(@Valid @RequestBody String question){
        try{
            questionService.addQuestion(question);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nie udalo sie utworzyc pytania dla ankiety, bad request");
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
//tworzenie jednego kodu dla ankiety

    @PostMapping("/authorizationCode/createAuthorizationCodeForQuestionnaire")
    public ResponseEntity<String> createAuthorizationCodeForQuestionnaire(@Valid @RequestBody Long questionnaireId ){
        try{
            System.out.println(questionnaireService.getOneQuestionnaire(questionnaireId).getQuestionnaireId());
            authorizationCodeService.createCodeForQuestionnaire(questionnaireService.getOneQuestionnaire(questionnaireId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nie udalo sie utworzyc kodu dla ankiety o id: "+questionnaireId);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //tworzenie ankiety
    @PostMapping("/questionnaire/AddQuestionnaire")
    public ResponseEntity<String> addQuestionnaireName(@Valid @RequestBody String questionnaireName ){
        try{
            Questionnaire questionnaire= new Questionnaire();
            questionnaire.setQuestionnaireName(questionnaireName);
            questionnaireService.addQuestionnaire(questionnaire);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("podane dane są nie poprawne");
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //tworzenie tokenow do generowanie kodow do ankiety

    @PostMapping("/token/createTokenForQuestionnaire")
    public ResponseEntity<String> createQuantityOfTokenForQuestionnaire(@Valid @RequestBody @RequestParam("questionnaireId") Long questionnaireId, @RequestParam("quantity") int quantity){
        try{
            tokenService.createQuantityOfTokensForCodeGenerator(questionnaireId,quantity);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nie udalo sie utworzyc token do generowania kodow dla ankiety o id: "+questionnaireId);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //tworzenie wybranej ilosci kodow dla ankiety
    @PostMapping("/authorizationCode/createQuantityOfAuthorizationCodeForQuestionnaire")
    public ResponseEntity<String> createQuantityOfAuthorizationCodeForQuestionnaire(@Valid @RequestBody @RequestParam("questionnaireId") Long questionnaireId, @RequestParam("quantity") int quantity){
        try{
            authorizationCodeService.createQuantityOfAuthorizationCodes(questionnaireService.getOneQuestionnaire(questionnaireId),quantity);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nie udalo sie utworzyc kodów dla ankiety o id: "+questionnaireId);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //tworzenie kodu do ankiety za pomoca tokenu, zwraca ostatni wygenerowany kod,jako string jezeli jest poprawny, jezeli nie to zwraca stringa z bledem, jako parametr ma otrzymac Long
    //tak czy siak tego strina trzeba wyswietlic
    @GetMapping("/token/createAndGetAuthorizationCode")
    public String createAndGetAuthorizationCode(@Valid @RequestBody Long tokenCode){
        try{ if(tokenService.checkused(tokenCode)){
            tokenService.used(tokenCode);
            authorizationCodeService.createCodeForQuestionnaire(questionnaireService.getOneQuestionnaire(tokenService.getQuestionnaireIdByTokenCode(tokenCode)));
            return authorizationCodeService.getLastCodForQuestionnaireId(tokenService.getQuestionnaireIdByTokenCode(tokenCode)).getAuthorizationCode().toString();
        }else return "Podany kod jest zuzyty";

        }catch (Exception e){
            return "Podany kod jest nieprawidłowy";
        }
    }

    //zwraca czy podany authorization code jest uzyty czy nie
    @GetMapping("/authorizationCode/getAuthorizationCode")
    public Boolean getAuthorizationCode(@Valid @RequestBody Long authorizationCode){
        return authorizationCodeService.getAuthorizationCodeByAuthorizationCode(authorizationCode).getUsed();
    }

    //wyswietlanie listy odpowiedzi do ankiety dla jednego authorization kodu / trzeba sprawdzic czy w authorization code pole used jest false czy true, jezeli jest true to znaczy, ze kod jest użyty
    // i wtedy wykonujemy ta metode

    //if != null wykonaj, else pokaz informacje "podany kod jest nie poprawny" - trzeba zrobic na froncie ta informacje
    @GetMapping("/userAnswer/getAllUserAnswerForQuestionnaire")
    public List<UserAnswer> getAllUserAnswerForQuestionnaire(@Valid @RequestBody Long authorizationCode){
        try{
            System.out.println(authorizationCodeService.getAuthorizationCodeByAuthorizationCode(authorizationCode).getQuestionnaire().getQuestionnaireId() +" + "+ authorizationCodeService.getAuthorizationCodeByAuthorizationCode(authorizationCode).getIdAuthorizationCode() );
            return userAnswerService.findUserAnswersByAuthorizationCode_IdAuthorizationCode(authorizationCodeService.getAuthorizationCodeByAuthorizationCode(authorizationCode).getIdAuthorizationCode());

        }catch (Exception e){
            return null;
        }
    }
    //if != null wykonaj, else pokaz informacje "podany kod jest nie poprawny" - trzeba zrobic na froncie ta informacje

    @GetMapping("/question/getAllQuestionForQuestionnaire")
    public List<Question> getAllQuestionForQuestionnaire(@Valid @RequestBody Long authorizationCode){
        try{
            return questionService.getAllFromQuestionnaire(authorizationCodeService.getAuthorizationCodeByAuthorizationCode(authorizationCode).getQuestionnaire().getQuestionnaireId());
        }catch (Exception e){
            return null;
        }
    }

    // wyswietlanie listy tokenow do twoerzenia kodow do wybranej ankiety

    @GetMapping("/token/getAllTokensForQuestionnaire")
    public List<Token> getAllTokensForQuestionnaire(@Valid @RequestBody Long questionnaireId ){
        return tokenService.getAllForQuestionnaire(questionnaireId);
    }

    // wyswietlanie wszystkich danych o ankietach - potrzebne są nazwy - beda tam pola z nazwami
    @GetMapping("/questionnaire/getAllQuestionnaire")
    public List<Questionnaire> getAllQuestionnarie(){
        return questionnaireService.getAll();
    }
    // wyswietlanie listy kodów do ankiety
    @GetMapping("/authorizationCode/getAllAuthorizationCodesForQuestionnaire")
    public List<AuthorizationCode> getAllAuthorizationCodesForQuestionnaire(@Valid @RequestBody Long questionnaireId ){
        return authorizationCodeService.getAllByQuestionnaireId(questionnaireId);
    }
}
