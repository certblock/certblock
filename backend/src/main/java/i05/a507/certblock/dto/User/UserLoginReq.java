package i05.a507.certblock.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginReq {
    private String email;
    private String password;
}
