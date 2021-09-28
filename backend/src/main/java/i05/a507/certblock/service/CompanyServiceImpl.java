package i05.a507.certblock.service;

import i05.a507.certblock.domain.*;
import i05.a507.certblock.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	SubmitRepository submitRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CompanyRepository companyRepository;

	@Override
	public Company getCompany(int companyId){
		return companyRepository.findById(companyId);
	}

	@Override
	public List<Student> selectAllApplicant(int companyId) {
		List<Student> studentList = new ArrayList<>();

		//해당기업에 모든 제출 기록 확인
		List<Submit> submitList = submitRepository.findByCompanyId(companyId).orElse(null);
		for (Submit submit: submitList) {
			Student student = submit.getCertificate().getUniversityStudent().getStudent();
			if(!studentList.contains(student)) studentList.add(student);
		}
		return studentList;
	}

	@Override
	public List<Certificate> selectApplicantCert(int companyId, int studentId) {
		List<Certificate> certificateList = new ArrayList<>();

		List<Submit> submitList = submitRepository.findByCompanyId(companyId).orElse(null);
		for (Submit submit: submitList) {
			if(submit.getCertificate().getUniversityStudent().getStudent().getId()==studentId)
				certificateList.add(submit.getCertificate());
		}
		return certificateList;
	}

}
