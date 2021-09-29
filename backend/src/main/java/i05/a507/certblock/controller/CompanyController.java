package i05.a507.certblock.controller;

import i05.a507.certblock.dto.Company.CompanyApplicantCertRes;
import i05.a507.certblock.dto.Company.CompanyApplicantRes;
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
    private CompanyService companyService;

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






}

