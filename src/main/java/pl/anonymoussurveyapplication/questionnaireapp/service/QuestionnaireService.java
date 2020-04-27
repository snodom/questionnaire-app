package pl.anonymoussurveyapplication.questionnaireapp.service;

import pl.anonymoussurveyapplication.questionnaireapp.model.Questionnaire;

import java.util.List;

public interface QuestionnaireService {
    List<Questionnaire> getAll();
    Questionnaire getOneQuestionnaire(Long questionnaireId);
    void addQuestionnaire(Questionnaire questionnaire);
    void deleteQuestionnaire(Long questionnaireId);
}
