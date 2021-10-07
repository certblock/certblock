package i05.a507.certblock.repository;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.Submit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmitRepository extends JpaRepository<Submit, Integer> {
    List<Submit> findByCompanyId(int companyId);
    List<Submit> findByCertificate(Certificate certificate);
}