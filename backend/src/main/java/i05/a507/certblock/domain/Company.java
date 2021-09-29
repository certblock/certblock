package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class Company extends User{
    @OneToMany(mappedBy = "company")
    private List<Submit> submitList;
}
