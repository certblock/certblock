package i05.a507.certblock.service;

import i05.a507.certblock.domain.User;
import i05.a507.certblock.dto.User.UserLoginReq;
import i05.a507.certblock.dto.User.UserModifyReq;
import i05.a507.certblock.dto.User.UserRegisterReq;

import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	boolean registUser(UserRegisterReq userRegisterReq);
	List<User> getAllUsers();
	User loginUser(UserLoginReq userLoginReq);
	User getUser(int userId);
	boolean modifyUser(int userId, UserModifyReq userModifyReq);
	boolean deleteUser(int userId);
}
