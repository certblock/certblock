package i05.a507.certblock.service;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.Student;
import i05.a507.certblock.dto.Student.StudentCertRes;
import i05.a507.certblock.dto.Student.StudentRegistUniReq;
import i05.a507.certblock.dto.Student.StudentUniversitiesRes;

import java.util.List;

public interface StudentService {
	Student getStudent(int userId);
	List<StudentUniversitiesRes>  getStudentUniversities(int studentId);
	boolean registStudentUniversity (int studentId, int universityId, StudentRegistUniReq studentRegistUniReq);
    List<StudentCertRes> getStudentCertificate(int studentId, int universityId);
    boolean issueCertificate(int studentId, int universityId, int certId);
	List<StudentCertRes> getAllCertificate(int studentId);
}
