package ezequiel.trevorassignment13.repository;

import ezequiel.trevorassignment13.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
