package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@IdClass(UniversityStudentId.class)
public class UniversityStudent {

    @Id
    @ManyToOne
    @JoinColumn(name="university_id")
    private University university;
    @Id
    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    private int type;

    @OneToMany(mappedBy = "universityStudent")
    private List<Certificate> certificateList;
}
