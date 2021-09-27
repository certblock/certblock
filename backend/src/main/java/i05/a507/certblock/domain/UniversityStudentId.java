package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UniversityStudentId implements Serializable {

    private int university;
    private int student;

    public UniversityStudentId() {}

    public UniversityStudentId(int university, int student) {
        this.university = university;
        this.student = student;
    }
}
