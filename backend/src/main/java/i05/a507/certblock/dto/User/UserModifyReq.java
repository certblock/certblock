package i05.a507.certblock.dto.User;

import lombok.Getter;

import java.util.Date;

@Getter
public class UserModifyReq {
    private String name;
    private String phone;
    private String email;
    private String pass;
    private String pass_confirm;
    private Date birth;
    private int type;
}