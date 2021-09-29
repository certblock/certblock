package i05.a507.certblock.repository;

import i05.a507.certblock.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, String> {
    Optional<University> findById(int universityId);
    Optional<University> findByEmail(String email);

}