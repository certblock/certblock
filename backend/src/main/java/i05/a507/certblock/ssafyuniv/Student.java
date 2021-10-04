package i05.a507.certblock.ssafyuniv;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "SsafyunivStudent")
@Table(name = "SsafyunivStudent")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int type;
    private String path1;
    private String path2;
}
