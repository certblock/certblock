package i05.a507.certblock.dto.User;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserLoginRes {
    int userId;
    int studentId;
    int companyId;
    int type;
    String name;
    String phone;
    String email;
    Date birth;


}
