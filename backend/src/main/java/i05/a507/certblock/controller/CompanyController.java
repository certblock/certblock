package i05.a507.certblock.controller;

import i05.a507.certblock.dto.Company.CertReq;
import i05.a507.certblock.dto.Company.CompanyApplicantCertRes;
import i05.a507.certblock.dto.Company.CompanyApplicantRes;
import i05.a507.certblock.dto.submit.SubmitRes;
import i05.a507.certblock.service.CompanyService;
import i05.a507.certblock.service.SubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final SubmitService submitService;

   //지원자 목록 조회
    @GetMapping("/{companyId}/students")
    public ResponseEntity<?> selectAllApplicant(@PathVariable int companyId) {
        List<CompanyApplicantRes> carList = companyService.selectAllApplicant(companyId);
        return ResponseEntity.status(200).body(carList);
    }

    //특정 지원자의 증명서 목록 조회
    @GetMapping("/{companyId}/students/{studentId}")
    public ResponseEntity<?> selectAllApplicant(@PathVariable int companyId, @PathVariable int studentId) {
        List<CompanyApplicantCertRes> cacrList = companyService.selectApplicantCert(companyId,studentId);
        return ResponseEntity.status(200).body(cacrList);
    }

    // 제출 이력 생성
    @PostMapping("/{companyId}/certificates/{certificateId}")
    public ResponseEntity<?> createSubmit(@PathVariable int companyId, @PathVariable int certificateId) {
        SubmitRes submitRes = submitService.createSubmit(companyId, certificateId);
        return ResponseEntity.status(200).body(submitRes);
    }

    // 여러 개의 제출 이력 생성
    @PostMapping("/{companyId}/certificates")
    public ResponseEntity<?> createSubmits(@PathVariable int companyId, @RequestBody int[] certIds) {
        List<SubmitRes> submitResList = submitService.createSubmits(companyId, certIds);
        return ResponseEntity.status(200).body(submitResList);
    }

    // 제출 이력 조회
    @GetMapping("/{companyId}/certificates")
    public ResponseEntity<?> getSubmits(@PathVariable int companyId) {
        List<SubmitRes> submitResList = submitService.getSubmits(companyId);
        return ResponseEntity.status(200).body(submitResList);
    }

}

