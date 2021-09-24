package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class UniversityStudent {

    @EmbeddedId
    UniversityStudentId universityStudentId;

    int type;
}
