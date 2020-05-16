package pl.anonymoussurveyapplication.questionnaireapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.anonymoussurveyapplication.questionnaireapp.model.AuthorizationCode;


import java.util.List;

@Repository
public interface AuthorizationCodeRepository extends JpaRepository<AuthorizationCode, Long>{
    List<AuthorizationCode> findAll();
    List<AuthorizationCode> findAuthorizationCodesByQuestionnaireQuestionnaireId(Long questionnaireId);
    AuthorizationCode findByAuthorizationCode(Long authorizationCode);
    AuthorizationCode findByIdAuthorizationCode(Long authorizationCodeId);
}