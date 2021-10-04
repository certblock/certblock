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
    private int id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="student_id"),
            @JoinColumn(name="university_id"),
    })
    private UniversityStudent universityStudent;

    int type;
    @Column(name = "date", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;
    private String hash;
    private Boolean issuance;

    @OneToMany(mappedBy = "certificate")
    private List<Submit> submitList;
}
