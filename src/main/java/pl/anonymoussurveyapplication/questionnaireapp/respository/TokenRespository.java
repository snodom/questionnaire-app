package pl.anonymoussurveyapplication.questionnaireapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.anonymoussurveyapplication.questionnaireapp.model.Token;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface TokenRespository extends JpaRepository<Token, Long> {
    List<Token> findAll();
    Token findByTokenCode(@NotNull String tokenCode);
}
