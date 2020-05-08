package pl.anonymoussurveyapplication.questionnaireapp.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.anonymoussurveyapplication.questionnaireapp.model.User;

import javax.validation.constraints.NotNull;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findAllByLogin(@NotNull String login);
}
