package i05.a507.certblock.controller;

import i05.a507.certblock.dto.BaseResponseBody;
import i05.a507.certblock.dto.Student.StudentUniversitiesRes;
import i05.a507.certblock.service.CompanyService;
import i05.a507.certblock.service.StudentService;
import i05.a507.certblock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    CompanyService companyService;

    //유저의 대학 목록 조회
    @GetMapping("/{studentId}/universities")
    public ResponseEntity<?> selectStudentUniversities(@PathVariable int studentId) {
        List<StudentUniversitiesRes> surList = studentService.getStudentUniversities(studentId);
        return ResponseEntity.status(200).body(surList);
    }

    //대학등록
    @PostMapping("/{studentId}/universities/{universityId}")
    public ResponseEntity<? extends BaseResponseBody> registStudentUniversity(
            @PathVariable int studentId, @PathVariable int universityId){

       boolean flg = studentService.registStudentUniversity(studentId,universityId);
       if(flg)  return ResponseEntity.status(200).body(BaseResponseBody.of(200, ""));
       else return ResponseEntity.status(200).body(BaseResponseBody.of(401, "해당 학교의 학생이 아닙니다"));
    }



}

