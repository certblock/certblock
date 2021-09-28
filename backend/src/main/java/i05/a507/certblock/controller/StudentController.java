package i05.a507.certblock.controller;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.dto.BaseResponseBody;
import i05.a507.certblock.dto.Student.StudentUniversitiesRes;
import i05.a507.certblock.repository.UniversityStudentRepository;
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
    @Autowired
    UniversityStudentRepository universityStudentRepository;

    //유저의 대학 목록 조회
    @GetMapping("/{studentId}/universities")
    public ResponseEntity<?> selectStudentUniversities(@PathVariable int studentId) {
        List<StudentUniversitiesRes> surList = studentService.getStudentUniversities(studentId);

        if(surList.size()==0) return ResponseEntity.status(404).body(BaseResponseBody.of(404, "등록된 학교가 존재하지 않습니다."));
        else return ResponseEntity.status(200).body(surList);
    }

    //대학 등록 (대학교 API 통해 재학생 인증 후)
    @PostMapping("/{studentId}/universities/{universityId}")
    public ResponseEntity<? extends BaseResponseBody> registStudentUniversity(
            @PathVariable int studentId, @PathVariable int universityId){

       boolean flg = studentService.registStudentUniversity(studentId,universityId);
       if(flg)  return ResponseEntity.status(200).body(BaseResponseBody.of(200, ""));
       //학생이 학교의 재학생이 맞는데도 등록 실패니 회원정보 입력하는데 오류있는거
       else return ResponseEntity.status(200).body(BaseResponseBody.of(401, "회원정보를 잘못입력하셨습니다."));
    }

    //유저의 특정 대학 증명서 목록 조회
    @GetMapping("/{studentId}/universities/{universityId}/certificates")
    public ResponseEntity<?> selectStudentCertificate(@PathVariable int studentId, @PathVariable int universityId){
        List<Certificate> certificateList = studentService.getStudentCertificate(studentId,universityId);

        if(certificateList.size()==0) return ResponseEntity.status(200).body(BaseResponseBody.of(404, "등록된 증명서가 존재하지 않습니다."));
        else return ResponseEntity.status(200).body(certificateList);
    }

    //유저의 특정 대학 증명서 발급
    @PutMapping("/{studentId}/universities/{universityId}/certificates/{certId}")
    public ResponseEntity<?> issueCertificate(@PathVariable int studentId, @PathVariable int universityId,@PathVariable int certId ){
        boolean flg = studentService.issueCertificate(studentId,universityId,certId);

        if(flg) return ResponseEntity.status(200).body(BaseResponseBody.of(200, ""));
        else return ResponseEntity.status(404).body(BaseResponseBody.of(401, "증명서를 발급받을 수 있는 권한이 없습니다."));
    }

    //유저의 증명서 발급 현황 조회
    @GetMapping("/{studentId}/certificates")
    public ResponseEntity<?> selectAllCertificate(@PathVariable int studentId){
        List<Certificate> certificateList = studentService.getAllCertificate(studentId);

        if(certificateList.size()==0) return ResponseEntity.status(200).body(BaseResponseBody.of(404, "등록된 증명서가 존재하지 않습니다."));
        else return ResponseEntity.status(200).body(certificateList);
    }



}

