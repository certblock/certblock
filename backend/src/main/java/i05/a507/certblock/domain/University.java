package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class University extends User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int universityId;

    @OneToMany
    @JoinColumn(name="universityId")
    List<UniversityStudent> universityStudentList;
}
