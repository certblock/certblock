package i05.a507.certblock.service;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.University;

import java.util.List;

public interface UniversityService {
    University getUniversity(int universityId);
    List<Certificate> selectAllCertificate(int universityId);
    List<Certificate> getStudentCertificate(int userId, int universityId);
}
