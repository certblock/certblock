package i05.a507.certblock.repository;

import i05.a507.certblock.domain.UniversityStudent;
import i05.a507.certblock.domain.UniversityStudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityStudentRepository extends JpaRepository<UniversityStudent, UniversityStudentId> {
    Optional<List<UniversityStudent>> findByStudentId(int StudentId);
    Optional<List<UniversityStudent>> findByUniversityId(int universityId);
    Optional<UniversityStudent> findByStudentIdAndUniversityId(int StudentId, int universityId);
}
