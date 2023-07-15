package ezequiel.trevorassignment13.repository;

import ezequiel.trevorassignment13.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u"
            + " left join fetch u.accounts"
            + " left join fetch u.address")
    Set<User> findAllUsersWithAccountAndAddress();

//    @Query("select u from User u"
//    + "left join fetch u.address"
//    + "left join fetch u.accounts"
//    + "where u.user_id= :user_id")
//    Optional<User> findByIdWithAddressAndAccount();

}
