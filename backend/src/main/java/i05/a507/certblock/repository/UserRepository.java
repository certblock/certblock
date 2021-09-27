package i05.a507.certblock.repository;

import i05.a507.certblock.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(int userId);
    Optional<User> findByEmailAndPassword(String email, String password);
}