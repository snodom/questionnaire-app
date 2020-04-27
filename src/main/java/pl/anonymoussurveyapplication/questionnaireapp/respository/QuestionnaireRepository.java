package pl.anonymoussurveyapplication.questionnaireapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.anonymoussurveyapplication.questionnaireapp.model.Questionnaire;

import java.util.List;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    List<Questionnaire> findAll();
    Questionnaire findByQuestionnaireId(Long questionnaireId);
}