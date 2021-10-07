package i05.a507.certblock.dto.Student;

import i05.a507.certblock.domain.Certificate;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentCertRes {
    private int certificateId;
    private int studentId;
    private int universityId;
    private int type; // 증명서 종류
    private boolean flg; // 발급 여부
    private Date date; // 발급 날짜
    private Date expiryDate; // 만료 날짜

    public static StudentCertRes of(Certificate certificate) {
        StudentCertRes scr = new StudentCertRes();
        scr.setCertificateId(certificate.getId());
        scr.setType(certificate.getType());
        scr.setFlg(certificate.getIssuance());
        scr.setDate(certificate.getIssuance()? certificate.getIssuanceDate() : null);
        scr.setExpiryDate(certificate.getIssuance()? certificate.getExpiryDate() : null);
        scr.setStudentId(certificate.getUniversityStudent().getStudent().getId());
        scr.setUniversityId(certificate.getUniversityStudent().getUniversity().getId());
        return scr;
    }
}
