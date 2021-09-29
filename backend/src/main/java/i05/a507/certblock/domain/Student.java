package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Student extends User{
    @OneToMany(mappedBy = "student")
    private List<UniversityStudent> universityStudentList;
}
