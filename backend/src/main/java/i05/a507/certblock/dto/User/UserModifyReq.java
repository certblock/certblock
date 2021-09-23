package i05.a507.certblock.dto.User;

import lombok.Getter;

import java.util.Date;

@Getter
public class UserModifyReq {
    String name;
    String phone;
    String email;
    String pass;
    String pass_confirm;
    Date birth;
}