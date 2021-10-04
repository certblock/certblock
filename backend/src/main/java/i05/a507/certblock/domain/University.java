package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class University extends User {

    @OneToMany(mappedBy = "university")
    private List<UniversityStudent> universityStudentList;

    private String apiServerBaseUrl;
}
