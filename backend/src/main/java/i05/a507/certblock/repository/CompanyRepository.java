package i05.a507.certblock.repository;

import i05.a507.certblock.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findById(int userId);
    Optional<Company> findByEmail(String email);
}
