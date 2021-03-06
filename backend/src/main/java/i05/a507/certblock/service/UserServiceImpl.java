package i05.a507.certblock.service;

import i05.a507.certblock.domain.Company;
import i05.a507.certblock.domain.Student;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.domain.User;
import i05.a507.certblock.dto.User.UserLoginReq;
import i05.a507.certblock.dto.User.UserLoginRes;
import i05.a507.certblock.dto.User.UserModifyReq;
import i05.a507.certblock.dto.User.UserRegisterReq;
import i05.a507.certblock.repository.CompanyRepository;
import i05.a507.certblock.repository.StudentRepository;
import i05.a507.certblock.repository.UniversityRepository;
import i05.a507.certblock.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.*;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	UniversityRepository universityRepository;

	@Override
	public boolean registUser(UserRegisterReq userRegisterReq)
			throws InvalidAlgorithmParameterException, NoSuchAlgorithmException,
			NoSuchProviderException, CipherException {

		int type = userRegisterReq.getType();
		Date birth = userRegisterReq.getBirth();
		String name = userRegisterReq.getName();
		String phone = userRegisterReq.getPhone();
		String email = userRegisterReq.getEmail();
		String pass = userRegisterReq.getPass();

		ECKeyPair ecKeyPair = Keys.createEcKeyPair();
		WalletFile newWallet = Wallet.createLight("1234", ecKeyPair);
		String walletAddress = "0x" + newWallet.getAddress();

		if(type==1){
			if(universityRepository.findByEmail(email).orElse(null)!=null) return false;
			University university = new University();
			university.setType(type);
			university.setBirth(birth);
			university.setName(name);
			university.setPhone(phone);
			university.setEmail(email);
			university.setPassword(pass);
			university.setWalletAddress(walletAddress);
			universityRepository.save(university);
		}else if(type==2){
			if(studentRepository.findByEmail(email).orElse(null)!=null) return false;
			Student student = new Student();
			student.setType(type);
			student.setBirth(birth);
			student.setName(name);
			student.setPhone(phone);
			student.setEmail(email);
			student.setPassword(pass);
			student.setWalletAddress(walletAddress);
			studentRepository.save(student);
		}else if(type==3){
			if(companyRepository.findByEmail(email).orElse(null)!=null) return false;
			Company company = new Company();
			company.setType(type);
			company.setBirth(birth);
			company.setName(name);
			company.setPhone(phone);
			company.setEmail(email);
			company.setPassword(pass);
			company.setWalletAddress(walletAddress);
			companyRepository.save(company);
		}
		return true;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserLoginRes loginUser(UserLoginReq userLoginReq) {
		String email = userLoginReq.getEmail();
		String password = userLoginReq.getPassword();

		User user = userRepository.findByEmailAndPassword(email,password).orElse(null);
		UserLoginRes ulr = new UserLoginRes();
		ulr.setId(user.getId());
		ulr.setEmail(user.getEmail());
		ulr.setName(user.getName());
		ulr.setPhone(user.getPhone());
		ulr.setBirth(user.getBirth());
		ulr.setType(user.getType());

		return ulr;
	}

	@Override
	public UserLoginRes getUser(int userId){
		User user = userRepository.findById(userId).orElse(null);
		UserLoginRes ulr = new UserLoginRes();
		ulr.setId(user.getId());
		ulr.setEmail(user.getEmail());
		ulr.setName(user.getName());
		ulr.setPhone(user.getPhone());
		ulr.setBirth(user.getBirth());
		ulr.setType(user.getType());
		return ulr;
	}

	@Override
	public boolean modifyUser(int userId, UserModifyReq userModifyReq) {
		User user = userRepository.findById(userId).orElse(null);
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
		User user = userRepository.findById(userId).orElse(null);
		if(user==null) return false;

		userRepository.delete(user);
		return true;
	}

	@Override
	public boolean checkUser(String email) {
		User user = userRepository.findByEmail(email).orElse(null);
		if(user==null) return false;
		return true;
	}

}
