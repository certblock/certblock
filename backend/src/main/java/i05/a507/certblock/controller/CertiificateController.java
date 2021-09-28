package i05.a507.certblock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certificate")
public class CertiificateController {

//    //증명서 발급
//    @PutMapping("/{certificateId}")
//    public ResponseEntity<?> issueCertificate(@PathVariable int studentId, @PathVariable int universityId, @PathVariable int certId ){
//        boolean flg = studentService.issueCertificate(studentId,universityId,certId);
//
//        if(flg) return ResponseEntity.status(200).body(BaseResponseBody.of(200, ""));
//        else return ResponseEntity.status(404).body(BaseResponseBody.of(401, "증명서를 발급받을 수 있는 권한이 없습니다."));
//    }
}
