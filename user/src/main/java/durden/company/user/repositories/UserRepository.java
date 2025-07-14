package durden.company.user.repositories;

import durden.company.user.entities.Role;
import durden.company.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    Boolean existsUserByUsername(String username);
    Boolean existsUserByEmail(String email);
}
