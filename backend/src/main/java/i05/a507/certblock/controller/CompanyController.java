package i05.a507.certblock.controller;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.Student;
import i05.a507.certblock.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

   //지원자 목록 조회
    @GetMapping("/{companyId}/students")
    public ResponseEntity<?> selectUniversity(@PathVariable int companyId) {
        List<Student> studentList = companyService.selectAllApplicant(companyId);
        return ResponseEntity.status(200).body(studentList);
    }

    //특정 지원자의 증명서 목록 조회
    @GetMapping("/{companyId}/students/{studentId}")
    public ResponseEntity<?> selectUniversity(@PathVariable int companyId, @PathVariable int studentId) {
        List<Certificate> certificateList = companyService.selectApplicantCert(companyId,studentId);
        return ResponseEntity.status(200).body(certificateList);
    }






}

