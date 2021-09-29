package i05.a507.certblock.controller;

import i05.a507.certblock.dto.BaseResponseBody;
import i05.a507.certblock.dto.University.UniversityCertiRes;
import i05.a507.certblock.dto.University.UniversitySelectRes;
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

    //모든 대학 조회
    @GetMapping("")
    public ResponseEntity<?> selectAllUniversity() {
        List<UniversitySelectRes> list = universityService.getAllUniversity();
        return ResponseEntity.status(200).body(list);
    }

   //대학 조회
    @GetMapping("/{universityId}")
    public ResponseEntity<?> selectUniversity(@PathVariable int universityId) {
        UniversitySelectRes usr = universityService.getUniversity(universityId);
        if(usr!=null) return ResponseEntity.status(200).body(usr);
        else return ResponseEntity.status(404).body(BaseResponseBody.of(404, "등록된 학교가 없습니다."));
    }

    //증명서 발급 기록 목록 조회
    @GetMapping("/{universityId}/certifcates")
    public ResponseEntity<?> selectAllCertificate(@PathVariable int universityId) {
        List<UniversityCertiRes> certificateList = universityService.selectAllCertificate(universityId);
        return ResponseEntity.status(200).body(certificateList);
    }

    //특적 학생의 발급 기록 조회
    @GetMapping("/{universityId}/users/{userId}/certificates")
    public ResponseEntity<?> selectAllCertificate(@PathVariable int universityId, @PathVariable int userId) {
        List<UniversityCertiRes> certificateList = universityService.getStudentCertificate(userId,universityId);
        return ResponseEntity.status(200).body(certificateList);
    }




}

