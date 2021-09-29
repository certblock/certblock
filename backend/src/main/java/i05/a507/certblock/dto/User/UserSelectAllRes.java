package i05.a507.certblock.dto.User;

import i05.a507.certblock.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSelectAllRes {
    private User user;
    private int userId = user.getId();
    private String userName = user.getName();
}
