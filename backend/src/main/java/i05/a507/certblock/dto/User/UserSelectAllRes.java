package i05.a507.certblock.dto.User;

import i05.a507.certblock.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSelectAllRes {
    User user;
    int userId = user.getUserId();
    String userName = user.getName();
}
