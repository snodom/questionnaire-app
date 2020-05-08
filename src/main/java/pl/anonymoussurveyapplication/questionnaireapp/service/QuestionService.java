package pl.anonymoussurveyapplication.questionnaireapp.service;

import pl.anonymoussurveyapplication.questionnaireapp.model.Question;

import java.util.List;

public interface QuestionService {
    void addQuestion(String question);
    void deleteQuestion(Long question_id);
    void deleteAllQuestions(Long questionnaire_id);
    List<Question> getAllFromQuestionnaire(Long questionnaire_id);
    Question getQuestionById(Long questionId);
}
