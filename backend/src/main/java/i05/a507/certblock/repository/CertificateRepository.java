package i05.a507.certblock.repository;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.UniversityStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, String> {
    Optional<List<Certificate>> findByUniversityStudent(UniversityStudent universityStudent);
}
