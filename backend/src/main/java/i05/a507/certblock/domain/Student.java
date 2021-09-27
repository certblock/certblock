package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Student extends User{

    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int studentId;

    @OneToMany(mappedBy = "student")
    List<UniversityStudent> universityStudentList;
}
