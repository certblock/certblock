package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UniversityStudentId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "universityId")
    University university;

    @ManyToOne
    @JoinColumn(name = "studentId")
    Student student;

}
