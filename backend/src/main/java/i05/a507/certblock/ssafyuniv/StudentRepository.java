package i05.a507.certblock.ssafyuniv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("SsafyunivStudentRepository")
public interface StudentRepository extends JpaRepository<Student, Integer> {
//    @Override
//    Optional<Student> findById(Long aLong);
}
