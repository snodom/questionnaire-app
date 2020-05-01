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

    // wyswietlanie wszystkich danych o ankietach - potrzebne są nazwy - beda tam pola z nazwami
    @GetMapping("/questionnaire/getAllQuestionnaire")
    public List<Questionnaire> getAllQuestionnarie(){
        return questionnaireService.getAll();
    }

    // wyswietlanie listy pytan do ankiety wybranej ankiety, jako parametr trzeba przeslac questionnaireId

    @GetMapping("/question/getAllQuestionForQuestionnaire")
    public List<Question> getAllQuestionForQuestionnaire(@Valid @RequestBody Long questionnaireId ){
        return questionService.getAllFromQuestionnaire(questionnaireId);
    }

    // wyswietlanie listy tokenow do twoerzenia kodow do wybranej ankiety

    @GetMapping("/token/getAllTokensForQuestionnaire")
    public List<Token> getAllTokensForQuestionnaire(@Valid @RequestBody Long questionnaireId ){
        return tokenService.getAllForQuestionnaire(questionnaireId);
    }

    // wyswietlanie listy kodów do ankiety
    @GetMapping("/authorizationCode/getAllAuthorizationCodesForQuestionnaire")
    public List<AuthorizationCode> getAllAuthorizationCodesForQuestionnaire(@Valid @RequestBody Long questionnaireId ){
        return authorizationCodeService.getAllByQuestionnaireId(questionnaireId);
    }

    //wyswietlanie listy odpowiedzi do ankiety dla jednego authorization kodu / trzeba sprawdzic czy w authorization cod pole used jest false czy true, jezeli jest true to znaczy, ze kod jest użyty
    @GetMapping("userAnswer/getAllUserAnswerForQuestionnaire")
    public List<UserAnswer> getAllUserAnswerForQuestionnaire(@Valid @RequestBody @RequestParam("questionnaireId") Long questionnaireId, @RequestParam("authorizationCodeId") Long authorizationCodeId){
       return userAnswerService.getAllAnswersByQuestionnaireIdAndAuthorizationCodeId(questionnaireId,authorizationCodeId);
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

    //tworzenie jednego kodu dla ankiety

    @PostMapping("/authorizationCode/createAuthorizationCodeForQuestionnaire")
    public ResponseEntity<String> createAuthorizationCodeForQuestionnaire(@Valid @RequestBody Long questionnaireId ){
        try{
        authorizationCodeService.createCodeForQuestionnaire(questionnaireService.getOneQuestionnaire(questionnaireId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nie udalo sie utworzyc kodu dla ankiety o id: "+questionnaireId);
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

    //dodawanie pytan do ankiety tutaj moze byc cos nie tak

    @PostMapping("/question/createQuestionForQuestionnaire")
    public ResponseEntity<String> createQuestionForQuestionnaire(@Valid @RequestBody @RequestParam("questionnaireId") Question question){
        try{
            questionService.addQuestion(question);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nie udalo sie utworzyc pytania dla ankiety");
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //tworzenie kodu za pomoca tokenu

    // przesylanie odpowiedzi do pytan

}
