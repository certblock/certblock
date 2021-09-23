package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Company extends User{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int companyId;
}
