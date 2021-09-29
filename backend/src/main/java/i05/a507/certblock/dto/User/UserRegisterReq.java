package i05.a507.certblock.dto.User;

import lombok.Getter;

import java.util.Date;

@Getter
public class UserRegisterReq {
    private String name;
    private String phone;
    private String email;
    private String pass;
    private Date birth;
    private int type;
}