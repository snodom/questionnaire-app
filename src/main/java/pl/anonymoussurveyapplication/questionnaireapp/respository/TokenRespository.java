package pl.anonymoussurveyapplication.questionnaireapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.anonymoussurveyapplication.questionnaireapp.model.Token;

import java.util.List;

@Repository
public interface TokenRespository extends JpaRepository<Token, Long> {
    List<Token> findAll();
}
