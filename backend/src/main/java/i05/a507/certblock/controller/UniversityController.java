package i05.a507.certblock.controller;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.dto.BaseResponseBody;
import i05.a507.certblock.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

   //대학 조회
    @GetMapping("/{universityId}")
    public ResponseEntity<?> selectUniversity(@PathVariable int universityId) {
        University university = universityService.getUniversity(universityId);
        return ResponseEntity.status(200).body(university);
    }

    //증명서 발급 기록 목록 조회
    @GetMapping("/{universityId}/certifcates")
    public ResponseEntity<?> selectAllCertificate(@PathVariable int universityId) {
        List<Certificate> certificateList = universityService.selectAllCertificate(universityId);

        if(certificateList.size()==0) return ResponseEntity.status(200).body(BaseResponseBody.of(404, "등록된 증명서가 존재하지 않습니다."));
        else return ResponseEntity.status(200).body(certificateList);
    }

    //특적 학생의 발급 기록 조회
    @GetMapping("/{universityId}/users/{userId}/certificates")
    public ResponseEntity<?> selectAllCertificate(@PathVariable int universityId, @PathVariable int userId) {
        List<Certificate> certificateList = universityService.getStudentCertificate(userId,universityId);

        if(certificateList.size()==0) return ResponseEntity.status(200).body(BaseResponseBody.of(404, "등록된 증명서가 존재하지 않습니다."));
        else return ResponseEntity.status(200).body(certificateList);
    }




}

