package durden.company.user.repositories;

import durden.company.user.entities.Role;
import durden.company.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
