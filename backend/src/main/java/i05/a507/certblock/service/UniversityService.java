package i05.a507.certblock.service;

import i05.a507.certblock.dto.University.UniversityCertiRes;
import i05.a507.certblock.dto.University.UniversitySelectRes;

import java.util.List;

public interface UniversityService {
    List<UniversitySelectRes> getAllUniversity();
    UniversitySelectRes getUniversity(int universityId);
    List<UniversityCertiRes> selectAllCertificate(int universityId);
    List<UniversityCertiRes> getStudentCertificate(int userId, int universityId);
}
