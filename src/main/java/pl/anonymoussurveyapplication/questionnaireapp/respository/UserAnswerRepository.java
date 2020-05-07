package pl.anonymoussurveyapplication.questionnaireapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.anonymoussurveyapplication.questionnaireapp.model.UserAnswer;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    List<UserAnswer> findAll();
    List<UserAnswer> findAllByQuestionQuestionnaireQuestionnaireIdAndAuthorizationCode_IdAuthorizationCode(Long questionnaireId, Long authorizationCodeId);
    List<UserAnswer> findUserAnswersByAuthorizationCode_IdAuthorizationCode(Long authorizationCodeId);
    UserAnswer findByAuthorizationCode_IdAuthorizationCodeAndQuestionQuestionId(Long questionId, Long authorizationCodeId);
}
