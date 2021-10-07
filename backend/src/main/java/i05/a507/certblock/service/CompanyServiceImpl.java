package i05.a507.certblock.service;

import i05.a507.certblock.domain.Company;
import i05.a507.certblock.domain.Submit;
import i05.a507.certblock.dto.Company.CompanyApplicantCertRes;
import i05.a507.certblock.dto.Company.CompanyApplicantRes;
import i05.a507.certblock.repository.CompanyRepository;
import i05.a507.certblock.repository.SubmitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	SubmitRepository submitRepository;

	@Override
	public Company getCompany(int companyId){
		return companyRepository.findById(companyId).orElse(null);
	}

	@Override
	public List<CompanyApplicantRes> selectAllApplicant(int companyId){
		List<CompanyApplicantRes> list = new ArrayList<>();

		List<Submit> subList = submitRepository.findByCompanyId(companyId);
		for (Submit submit: subList) {
			CompanyApplicantRes car = new CompanyApplicantRes();
			car.setStudentId(submit.getCertificate().getUniversityStudent().getStudent().getId());
			list.add(car);
		}

		return list;
	}

	@Override
	public List<CompanyApplicantCertRes> selectApplicantCert(int companyId, int studentId){
		List<CompanyApplicantCertRes> list = new ArrayList<>();

		List<Submit> subList = submitRepository.findByCompanyId(companyId);
		for (Submit submit: subList) {
			if(submit.getCertificate().getUniversityStudent().getStudent().getId()==studentId){
				CompanyApplicantCertRes car = new CompanyApplicantCertRes();
				car.setCertificateId(submit.getCertificate().getId());
				list.add(car);
			}
		}
		return list;
	}

}
