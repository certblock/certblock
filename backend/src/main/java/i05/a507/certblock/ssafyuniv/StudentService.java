package i05.a507.certblock.ssafyuniv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service("SsafyunivStudentService")
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    Student findById(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        return student;
    }

    String issueCertificate(int id, int type) {
        Student student = findById(id);
        if (type % 2 == 0) {
            return student.getPath2();
        } else {
            return student.getPath1();
        }
    }
}