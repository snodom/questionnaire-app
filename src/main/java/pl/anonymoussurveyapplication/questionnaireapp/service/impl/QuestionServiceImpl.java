package pl.anonymoussurveyapplication.questionnaireapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anonymoussurveyapplication.questionnaireapp.model.Question;
import pl.anonymoussurveyapplication.questionnaireapp.respository.QuestionRepository;
import pl.anonymoussurveyapplication.questionnaireapp.service.QuestionService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public void deleteAllQuestions(Long questionnaireId) {
        questionRepository.deleteAllByQuestionnaireQuestionnaireId(questionnaireId);
    }

    @Override
    public List<Question> getAllFromQuestionnaire(Long questionnaireId) {
        return questionRepository.findAllByQuestionnaireQuestionnaireId(questionnaireId);
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepository.findByQuestionId(questionId);
    }
}
