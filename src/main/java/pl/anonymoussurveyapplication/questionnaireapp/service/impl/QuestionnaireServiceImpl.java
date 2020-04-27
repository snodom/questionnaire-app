package pl.anonymoussurveyapplication.questionnaireapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anonymoussurveyapplication.questionnaireapp.model.Questionnaire;
import pl.anonymoussurveyapplication.questionnaireapp.respository.QuestionRepository;
import pl.anonymoussurveyapplication.questionnaireapp.respository.QuestionnaireRepository;
import pl.anonymoussurveyapplication.questionnaireapp.service.QuestionnaireService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
   private QuestionRepository questionRepository;

   @Override
    public List<Questionnaire> getAll(){
        return questionnaireRepository.findAll();
    }

    @Override
    @Transactional
    public void addQuestionnaire(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
    }

// usuwa wszystkie pytania do ankiety wraz z nią sama, czy potrzebne jest usuwanie pytan gdy jest to operacja kaskadowa? moze samo sie usunie - do przetestowania?

    @Override
    public void deleteQuestionnaire(Long questionnaireId) {
        questionRepository.deleteAllByQuestionnaireQuestionnaireId(questionnaireId);
        questionnaireRepository.deleteById(questionnaireId);
    }

    @Override
    public Questionnaire getOneQuestionnaire(Long questionnaireId) {
        return questionnaireRepository.findByQuestionnaireId(questionnaireId);
    }
}
