package i05.a507.certblock.service;

import i05.a507.certblock.domain.Student;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.domain.UniversityStudent;
import i05.a507.certblock.dto.Student.StudentUniversitiesRes;
import i05.a507.certblock.repository.StudentRepository;
import i05.a507.certblock.repository.UniversityRepository;
import i05.a507.certblock.repository.UniversityStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	@Override
	public Student getStudent(int userId) {
		Student student = studentRepository.findByUserId(userId).orElse(null);
		return student;
	}

	@Override
	public List<StudentUniversitiesRes> getStudentUniversities(int studentId){
		List<UniversityStudent> universityStudentList = universityStudentRepository.findByStudentId(studentId).orElseGet(null);
		List<StudentUniversitiesRes> surList = new ArrayList<>();

		for (UniversityStudent us:universityStudentList) {
			StudentUniversitiesRes sur = new StudentUniversitiesRes();

			University university = us.getUniversity();
			sur.setUniversityId(university.getUniversityId());
			sur.setUniversityName(university.getName());
			sur.setType(us.getType());

			surList.add(sur);
		}

		return surList;
	}

	@Override
	public boolean registStudentUniversity (int studentId, int universityId){
		Student student = studentRepository.findByStudentId(studentId).orElse(null);
		University university = universityRepository.findByUniversityId(universityId).orElse(null);
		if(student == null || university == null) return false;

		UniversityStudent universityStudent = new UniversityStudent();
		universityStudent.setUniversity(university);
		universityStudent.setStudent(student);
		universityStudentRepository.save(universityStudent);
		return true;
	}

}
