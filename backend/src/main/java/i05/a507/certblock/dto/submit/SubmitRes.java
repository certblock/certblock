package i05.a507.certblock.dto.submit;

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
    private LocalDateTime createdTime;

    public static SubmitRes of(Submit submit) {
        SubmitRes submitRes = new SubmitRes();
        submitRes.setId(submit.getId());
        submitRes.setCertificateId(submit.getCertificate().getId());
        submitRes.setCompanyId(submit.getCompany().getId());
        submitRes.setCertType(submit.getCertificate().getType());
        submitRes.setCreatedTime(submit.getCreatedTime());
        return submitRes;
    }
}
