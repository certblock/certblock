package i05.a507.certblock.service;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.Student;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.domain.UniversityStudent;
import i05.a507.certblock.dto.Student.StudentCertRes;
import i05.a507.certblock.dto.Student.StudentRegistUniReq;
import i05.a507.certblock.dto.Student.StudentUniversitiesRes;
import i05.a507.certblock.repository.CertificateRepository;
import i05.a507.certblock.repository.StudentRepository;
import i05.a507.certblock.repository.UniversityRepository;
import i05.a507.certblock.repository.UniversityStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	UniversityRepository universityRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	UniversityStudentRepository universityStudentRepository;
	@Autowired
	CertificateRepository certificateRepository;

	@Override
	public Student getStudent(int userId) {
		Student student = studentRepository.findById(userId).orElse(null);
		return student;
	}

	@Override
	public List<StudentUniversitiesRes> getStudentUniversities(int studentId){
		List<UniversityStudent> universityStudentList = universityStudentRepository.findByStudentId(studentId).orElse(null);
		List<StudentUniversitiesRes> surList = new ArrayList<>();

		for (UniversityStudent us: universityStudentList) {
			StudentUniversitiesRes sus = new StudentUniversitiesRes();
			sus.setUniversityId(us.getUniversity().getId());
			sus.setType(us.getType());
			sus.setUniversityName(us.getUniversity().getName());

			surList.add(sus);
		}
		return surList;
	}

	@Override
	public boolean registStudentUniversity (int studentId, int universityId, StudentRegistUniReq studentRegistUniReq){
		Student student = studentRepository.findById(studentId).orElse(null);
		University university = universityRepository.findById(universityId).orElse(null);
		UniversityStudent universityStudent = universityStudentRepository.findByStudentIdAndUniversityId(studentId,universityId).orElse(null);
		int type = studentRegistUniReq.getType();
		int studentIdInUniv = studentRegistUniReq.getStudentIdInUniv();
		if(student == null || university == null || universityStudent!=null) return false;

		UniversityStudent us = new UniversityStudent();
		us.setStudent(student);
		us.setUniversity(university);
		us.setType(type);
		us.setStudentIdInUniv(studentIdInUniv);
		universityStudentRepository.save(us);

		for (int i = 0; i < 2; i++){
			int certType = type * 2 - 1 + i;
			Certificate certificate = new Certificate();
			certificate.setUniversityStudent(us);
			certificate.setType(certType);
			certificate.setIssuance(false);
			certificateRepository.save(certificate);
		}

		return true;
	}

	@Override
	public List<StudentCertRes> getStudentCertificate(int studentId, int universityId) {
		List<StudentCertRes> issueCertList = new ArrayList<>();

		UniversityStudent universityStudent = universityStudentRepository.findByStudentIdAndUniversityId(studentId,universityId).orElse(null);

		List<Certificate> certificateList = certificateRepository.findByUniversityStudent(universityStudent).orElse(null);
		for (Certificate cf : certificateList) { //증명서 목록(1~6)
			StudentCertRes scr = new StudentCertRes();
			scr.setCertificateId(cf.getId());
			scr.setType(cf.getType());
			scr.setFlg(cf.getIssuance());
			issueCertList.add(scr);
		}
		return issueCertList;
	}

	@Override
	public boolean issueCertificate(int studentId, int universityId, int certId){
		UniversityStudent us = universityStudentRepository.findByStudentIdAndUniversityId(studentId,universityId).orElse(null);

		//졸업 여부 확인
		int status = us.getType();
		boolean flg = false; //사용자가 원하는 증명서 발급가능한지 여부

		//선택 증명서에 따른 발급여부 가능 확인
		if(status==1) { //학사 졸업 경우
			if(certId == 1 || certId == 2) flg = true;
			else flg = false;
		}else if(status == 2) { //석사 졸업인경우
			if(certId == 3 || certId == 4) flg = true;
			else flg = false;
		}else if(status == 3) { //박사 졸업인경우
			if(certId == 5 || certId == 6) flg = true;
			else flg = false;
		}else flg = false;

		if(flg){ //발급상태 가능하니까 발급해주기
			List<Certificate> list = certificateRepository.findByUniversityStudent(us).orElse(null);
			Certificate certificate = list.get(certId-1); //0부터 시작이니까

			Date now = new Date();
			certificate.setDate(now); //오늘 발급된거니까 날짜 바꿔주기
			certificate.setIssuance(true);
			certificateRepository.save(certificate);
			return true;
		}

		return false;
	}

	@Override
	public List<StudentCertRes> getAllCertificate(int studentId){
		List<StudentCertRes> certList = new ArrayList<>();

		//학교 목록 먼저 다 찾기
		List<UniversityStudent> universityStudentList = universityStudentRepository.findByStudentId(studentId).orElse(null);
		for (UniversityStudent us: universityStudentList) {
			//저장된 학교의 증명서 모두 찾기
			List<Certificate> certificateList = certificateRepository.findByUniversityStudent(us).orElse(null);
			for (Certificate cf : certificateList) {
				StudentCertRes scr = new StudentCertRes();
				scr.setCertificateId(cf.getId());
				scr.setType(cf.getType());
				scr.setFlg(cf.getIssuance());
				scr.setDate(cf.getIssuance()? cf.getDate() : null);
				scr.setStudentId(cf.getUniversityStudent().getStudent().getId());
				scr.setUniversityId(cf.getUniversityStudent().getUniversity().getId());
				scr.setHash(cf.getHash());
				certList.add(scr);
			}
		}
		return certList;
	}
}
