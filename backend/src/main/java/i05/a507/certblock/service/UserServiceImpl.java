package i05.a507.certblock.service;

import i05.a507.certblock.domain.User;
import i05.a507.certblock.dto.User.UserLoginReq;
import i05.a507.certblock.dto.User.UserModifyReq;
import i05.a507.certblock.dto.User.UserRegisterReq;
import i05.a507.certblock.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	@Override
	public void registUser(UserRegisterReq userRegisterReq){

		int type = userRegisterReq.getType();
		Date birth = userRegisterReq.getBirth();
		String name = userRegisterReq.getName();
		String phone = userRegisterReq.getPhone();
		String email = userRegisterReq.getEmail();
		String pass = userRegisterReq.getPass();

		User user = new User();
		user.setType(type);
		user.setBirth(birth);
		user.setName(name);
		user.setPhone(phone);
		user.setEmail(email);
		user.setPassword(pass);
		user.setAddress(null); //처음에는 지갑주소없으니까

		userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User loginUser(UserLoginReq userLoginReq) {
		String email = userLoginReq.getEmail();
		String password = userLoginReq.getPassword();

		User user = userRepository.findByEmailAndPassword(email,password).orElse(null);
		return user;
	}

	@Override
	public User getUser(int userId){
		User user = userRepository.findByUserId(userId).orElse(null);
		return user;
	}

	@Override
	public boolean modifyUser(int userId, UserModifyReq userModifyReq) {
		User user = userRepository.findByUserId(userId).orElse(null);
		if(user==null) return false;

		user.setName(userModifyReq.getName());
		user.setPhone(userModifyReq.getPhone());
		user.setEmail(userModifyReq.getEmail());
		user.setPassword(userModifyReq.getPass());
		user.setBirth(userModifyReq.getBirth());
		userRepository.save(user);
		return true;
	}

	@Override
	public boolean deleteUser(int userId) {
		User user = userRepository.findByUserId(userId).orElse(null);
		if(user==null) return false;

		userRepository.delete(user);
		return true;
	}

}
