package i05.a507.certblock.dto.Student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCertRes {
    private int certificateId;
    private int type; //증명서 종류
    private boolean flg; //발급여부
}
