package i05.a507.certblock.repository;

import i05.a507.certblock.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findById(int id);
    Optional<Student> findByStudentId(int studentId);
}
