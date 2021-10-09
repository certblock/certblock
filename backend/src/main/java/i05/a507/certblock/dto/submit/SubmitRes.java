package i05.a507.certblock.dto.submit;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.Submit;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubmitRes {
    private int id;
    private int certificateId;
    private int companyId;
    private int certType;
    private String applicantName;
    private LocalDateTime createdTime;
    private LocalDateTime expiryDate;
    private int univType;
    private String univName;
    private int noticeId;
    private String noticeTitle;

    public static SubmitRes of(Submit submit) {
        SubmitRes submitRes = new SubmitRes();
        Certificate certificate = submit.getCertificate();
        submitRes.setId(submit.getId());
        submitRes.setCertificateId(submit.getCertificate().getId());
        submitRes.setCompanyId(submit.getCompany().getId());
        submitRes.setCertType(submit.getCertificate().getType());
        submitRes.setApplicantName(certificate.getUniversityStudent().getStudent().getName());
        submitRes.setCreatedTime(submit.getCreatedTime());
        submitRes.setExpiryDate(submit.getExpiryDate());
        submitRes.setUnivName(certificate.getUniversityStudent().getUniversity().getName());
        submitRes.setUnivType(certificate.getUniversityStudent().getUniversity().getType());
        submitRes.setNoticeId(submit.getNoticeId());
        submitRes.setNoticeTitle(submit.getNoticeTitle());
        return submitRes;
    }
}
