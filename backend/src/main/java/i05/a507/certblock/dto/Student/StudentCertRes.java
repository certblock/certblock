package i05.a507.certblock.dto.Student;

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
    private String hash;
}
