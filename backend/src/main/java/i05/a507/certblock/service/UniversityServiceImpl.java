package i05.a507.certblock.service;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.domain.UniversityStudent;
import i05.a507.certblock.dto.University.UniversityCertiRes;
import i05.a507.certblock.dto.University.UniversitySelectRes;
import i05.a507.certblock.repository.CertificateRepository;
import i05.a507.certblock.repository.UniversityRepository;
import i05.a507.certblock.repository.UniversityStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
	public List<UniversitySelectRes> getAllUniversity() {
		List<University> universityList = universityRepository.findAll();

		List<UniversitySelectRes> usrList = new ArrayList<>();
		for (University us: universityList) {
			UniversitySelectRes usr = new UniversitySelectRes();
			usr.setUniversityId(us.getId());
			usr.setName(us.getName());
			usrList.add(usr);
		}
		return usrList;
	}

	@Override
	public UniversitySelectRes getUniversity(int universityId) {
		University university = universityRepository.findById(universityId).orElse(null);
		UniversitySelectRes usr = new UniversitySelectRes();
		usr.setUniversityId(universityId);
		usr.setName(university.getName());
		return usr;
	}

	@Override
	public List<UniversityCertiRes> selectAllCertificate(int universityId) {
		List<UniversityCertiRes> certList = new ArrayList<>();

		//학교 목록 먼저 다 찾기
//		List<UniversityStudent> universityStudentList = universityStudentRepository.findByUniversityId(universityId).orElse(new ArrayList<>());
		University university = universityRepository.findById(universityId).orElseThrow(RuntimeException::new);
		List<Certificate> certificateList = certificateRepository
				.findByUniversityStudent_UniversityAndIssuance(university, true).orElse(new ArrayList<>());
		certificateList.sort((certificate, t1) -> t1.getIssuanceDate().compareTo(certificate.getIssuanceDate()));
		for (Certificate cf : certificateList) {
//			UniversityCertiRes ucr = new UniversityCertiRes();
//			ucr.setCertificateHistoryId(cf.getId());
			certList.add(UniversityCertiRes.of(cf));
		}
		return certList;
	}

	@Override
	public List<UniversityCertiRes> getStudentCertificate(int userId, int universityId) {
		List<UniversityCertiRes> issueCertList = new ArrayList<>();

		UniversityStudent universityStudent = universityStudentRepository.findByStudentIdAndUniversityId(userId,universityId).orElse(null);

		List<Certificate> certificateList = certificateRepository.findByUniversityStudent(universityStudent).orElse(null);
		for (Certificate cf : certificateList) { //증명서 목록(1~6)
			//증명서가 발급된 경우만 -> 조회
			if (cf.getIssuance()) {
//				UniversityCertiRes ucr = new UniversityCertiRes();
//				ucr.setCertificateHistoryId(cf.getId());
				issueCertList.add(UniversityCertiRes.of(cf));
			}
		}
		return issueCertList;
	}


}
