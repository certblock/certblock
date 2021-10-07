package i05.a507.certblock.dto.University;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.UniversityStudent;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UniversityCertiRes {
    private int certId; //증명서 아이디
    private int studentIdInUniv; // 학번
    private String studentName; // 학번
    private Date issuanceDate; // 발급일
    private int certType; // 증명서 종류

    public static UniversityCertiRes of(Certificate cf) {
        UniversityCertiRes res = new UniversityCertiRes();
        UniversityStudent us = cf.getUniversityStudent();
        res.setCertId(cf.getId());
        res.setStudentIdInUniv(us.getStudentIdInUniv());
        res.setStudentName(us.getStudent().getName());
        res.setIssuanceDate(cf.getIssuanceDate());
        res.setCertType(cf.getType());
        return res;
    }
}
