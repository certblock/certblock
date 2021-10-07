package i05.a507.certblock.repository;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.domain.UniversityStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, String> {
    Optional<List<Certificate>> findByUniversityStudent(UniversityStudent universityStudent);
    Optional<List<Certificate>> findByUniversityStudent_UniversityAndIssuance(University university, boolean issuance);
    Optional<Certificate> findById(int certificateId);

    @Query("select c from Certificate c where c.id in :ids" )
    List<Certificate> findByIds(@Param("ids") List<Integer> certIds);
}
