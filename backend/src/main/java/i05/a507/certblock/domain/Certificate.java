package i05.a507.certblock.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Certificate {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="university_id"),
            @JoinColumn(name="student_id")
    })
    UniversityStudent universityStudent;

    int type;
    @Column(name = "date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    Date date;
    String hash;
    Boolean issuance;

    @OneToMany(mappedBy = "certificate")
    List<Submit> submitList;
}
