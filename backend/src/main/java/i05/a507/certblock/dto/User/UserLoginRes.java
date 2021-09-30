package i05.a507.certblock.dto.User;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserLoginRes {
    private int id;
    private String name;
    private String phone;
    private String email;
    private Date birth;
    private int type;
}