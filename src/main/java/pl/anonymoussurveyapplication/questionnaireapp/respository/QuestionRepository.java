package pl.anonymoussurveyapplication.questionnaireapp.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pl.anonymoussurveyapplication.questionnaireapp.model.Question;


import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAll();
    List<Question> findAllByQuestionnaireQuestionnaireId(Long questionnaireId);
    void deleteAllByQuestionnaireQuestionnaireId(Long questionnaireId);

}
