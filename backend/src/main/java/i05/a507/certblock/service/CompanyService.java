package i05.a507.certblock.service;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.Company;
import i05.a507.certblock.domain.Student;

import java.util.List;

public interface CompanyService {
    List<Student> selectAllApplicant(int companyId);
    List<Certificate> selectApplicantCert(int companyId, int studentId);
    Company getCompany(int companyId);
}
