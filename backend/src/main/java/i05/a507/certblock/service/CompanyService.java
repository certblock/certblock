package i05.a507.certblock.service;

import i05.a507.certblock.domain.Company;
import i05.a507.certblock.dto.Company.CompanyApplicantCertRes;
import i05.a507.certblock.dto.Company.CompanyApplicantRes;

import java.util.List;

public interface CompanyService {
    Company getCompany(int companyId);
    List<CompanyApplicantRes> selectAllApplicant(int companyId);
    List<CompanyApplicantCertRes> selectApplicantCert(int companyId, int studentId);
}
