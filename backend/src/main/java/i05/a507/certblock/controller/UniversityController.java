package i05.a507.certblock.controller;

import i05.a507.certblock.domain.University;
import i05.a507.certblock.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    //대학 조회
    @GetMapping("/{universityId}")
    public ResponseEntity<?> selectUniversity(@PathVariable int universityId) {
        University university = universityService.getUniversity(universityId);
        return ResponseEntity.status(200).body(university);
    }

    //증명서 발급 기록 목록 조회

    //특적 학생의 발급 기록 조회


}

