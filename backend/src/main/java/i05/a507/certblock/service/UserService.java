package i05.a507.certblock.service;

import i05.a507.certblock.domain.User;
import i05.a507.certblock.dto.User.UserLoginReq;
import i05.a507.certblock.dto.User.UserLoginRes;
import i05.a507.certblock.dto.User.UserModifyReq;
import i05.a507.certblock.dto.User.UserRegisterReq;
import org.web3j.crypto.CipherException;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	boolean registUser(UserRegisterReq userRegisterReq) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException;
	List<User> getAllUsers();
	UserLoginRes loginUser(UserLoginReq userLoginReq);
	UserLoginRes getUser(int userId);
	boolean modifyUser(int userId, UserModifyReq userModifyReq);
	boolean deleteUser(int userId);
	boolean checkUser(String email);
}
