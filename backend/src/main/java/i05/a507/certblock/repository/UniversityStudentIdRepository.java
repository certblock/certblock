package i05.a507.certblock.repository;

import i05.a507.certblock.domain.UniversityStudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityStudentIdRepository extends JpaRepository<UniversityStudentId, String> {
    Optional<List<UniversityStudentId>> findByStudentId(int StudentId);
}
