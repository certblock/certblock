package i05.a507.certblock.service;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.domain.UniversityStudent;
import i05.a507.certblock.repository.CertificateRepository;
import i05.a507.certblock.repository.UniversityRepository;
import i05.a507.certblock.repository.UniversityStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("universityService")
public class UniversityServiceImpl implements UniversityService {

	@Autowired
	UniversityRepository universityRepository;
	@Autowired
	UniversityStudentRepository universityStudentRepository;
	@Autowired
	CertificateRepository certificateRepository;

	@Override
	public University getUniversity(int universityId) {
		return universityRepository.findById(universityId).orElse(null);
	}

	@Override
	public List<Certificate> selectAllCertificate(int universityId) {
		List<Certificate> certList = new ArrayList<>();

		//학교 목록 먼저 다 찾기
		List<UniversityStudent> universityStudentList = universityStudentRepository.findByUniversityId(universityId).orElse(null);
		for (UniversityStudent us: universityStudentList) {
			//저장된 학교의 증명서 모두 찾기
			List<Certificate> certificateList = certificateRepository.findByUniversityStudent(us).orElse(null);
			for (Certificate cf : certificateList) {
				if(cf.getIssuance()) certList.add(cf);
			}
		}
		return certList;
	}

	@Override
	public List<Certificate> getStudentCertificate(int userId, int universityId) {
		List<Certificate> issueCertList = new ArrayList<>();

		UniversityStudent universityStudent = universityStudentRepository.findByStudentIdAndUniversityId(userId,universityId).orElse(null);

		List<Certificate> certificateList = certificateRepository.findByUniversityStudent(universityStudent).orElse(null);
		for (Certificate cf : certificateList) { //증명서 목록(1~6)
			//증명서가 발급된 경우만 -> 조회
			if (cf.getIssuance()) issueCertList.add(cf);
		}
		return issueCertList;
	}


}
