package i05.a507.certblock.controller;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.dto.BaseResponseBody;
import i05.a507.certblock.dto.Student.StudentCertRes;
import i05.a507.certblock.service.CertificateService;
import i05.a507.certblock.service.StudentService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.parser.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/certificate")
@RequiredArgsConstructor
public class CertificateController {
    final private StudentService studentService;
    final private CertificateService certificateService;

//    //증명서 발급
//    @PutMapping("/{certificateId}")
//    public ResponseEntity<?> issueCertificate(@PathVariable int studentId, @PathVariable int universityId, @PathVariable int certId ){
//        boolean flg = studentService.issueCertificate(studentId,universityId,certId);
//
//        if(flg) return ResponseEntity.status(200).body(BaseResponseBody.of(200, ""));
//        else return ResponseEntity.status(404).body(BaseResponseBody.of(401, "증명서를 발급받을 수 있는 권한이 없습니다."));
//    }

    @PutMapping("/{certificateId}")
    public ResponseEntity<?> issueCertificate(@PathVariable int certificateId)
            throws Exception {
        StudentCertRes studentCertRes = certificateService.issueCertificate(certificateId);
        return ResponseEntity.status(200).body(studentCertRes);
    }

    @GetMapping("/{certificateId}")
    public ResponseEntity<?> retrieveCertificate(@PathVariable int certificateId)
            throws Exception {
        String base64Str = certificateService.retrieveCertificate(certificateId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, base64Str));
    }
}
