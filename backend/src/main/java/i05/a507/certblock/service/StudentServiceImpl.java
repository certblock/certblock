package i05.a507.certblock.service;

import i05.a507.certblock.domain.Student;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.domain.UniversityStudent;
import i05.a507.certblock.dto.Student.StudentUniversitiesRes;
import i05.a507.certblock.repository.StudentRepository;
import i05.a507.certblock.repository.UniversityRepository;
import i05.a507.certblock.repository.UniversityStudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	UniversityRepository universityRepository;
	StudentRepository studentRepository;
	UniversityStudentRepository universityStudentRepository;

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
	public boolean registStudentUniversity (int studentId, int universityId){
		Student student = studentRepository.findByStudentId(studentId).orElse(null);
		University university = universityRepository.findByUniversityId(universityId).orElse(null);
		if(student == null || university == null) return false;

		UniversityStudent us = new UniversityStudent();
		us.setStudent(student);
		us.setUniversity(university);
		universityStudentRepository.save(us);
		return true;
	}

}
