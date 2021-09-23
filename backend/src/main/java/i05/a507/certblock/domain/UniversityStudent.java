package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class UniversityStudent implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "universityId")
    University university;

    @Id
    @ManyToOne
    @JoinColumn(name = "studentId")
    Student student;

    int type;

}
