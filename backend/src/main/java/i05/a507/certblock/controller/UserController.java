package i05.a507.certblock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import i05.a507.certblock.domain.User;
import i05.a507.certblock.dto.BaseResponseBody;
import i05.a507.certblock.dto.User.UserLoginReq;
import i05.a507.certblock.dto.User.UserLoginRes;
import i05.a507.certblock.dto.User.UserModifyReq;
import i05.a507.certblock.dto.User.UserRegisterReq;
import i05.a507.certblock.service.CompanyService;
import i05.a507.certblock.service.SmsService;
import i05.a507.certblock.service.StudentService;
import i05.a507.certblock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchProviderException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private SmsService smsService;

    //회원가입
    @PostMapping("")
    public ResponseEntity<? extends BaseResponseBody> registUser(@RequestBody UserRegisterReq userRegisterReq)
            throws InvalidAlgorithmParameterException, CipherException, NoSuchAlgorithmException,
            NoSuchProviderException {

        int type = userRegisterReq.getType();
        if(type==1 || type==2 || type == 3) {
            boolean flg = userService.registUser(userRegisterReq);
            if(flg) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
            else return ResponseEntity.status(400).body(BaseResponseBody.of(400, "등록된 회원입니다."));
        }
        else return ResponseEntity.status(400).body(BaseResponseBody.of(400, "회원 가입에 실패했습니다."));
    }

    //유저 목록 조회
    @GetMapping("")
    public ResponseEntity<?> selectAllUsers() throws JsonProcessingException, ParseException, UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        smsService.sendSms("01030187019","hey");
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.status(200).body(userList);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginReq userLoginReq){
        UserLoginRes user = userService.loginUser(userLoginReq);
        if(user != null) return ResponseEntity.status(200).body(user);
        else return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 회원입니다."));
    }

    //특정 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<?> selectUser(@PathVariable int userId) {
        UserLoginRes user = userService.getUser(userId);
        if(user!=null) return ResponseEntity.status(200).body(user);
        else return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않는 회원입니다."));
    }

    //회원 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<? extends BaseResponseBody> modifyUser(
            @PathVariable int userId, @RequestBody UserModifyReq userModifyReq) {

      boolean flg = userService.modifyUser(userId,userModifyReq);
      try{
          if(flg)  return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원정보 수정이 완료되었습니다."));
          else return ResponseEntity.status(404).body(BaseResponseBody.of(404, "수정할 회원이 존재하지 않습니다."));
      }catch (Exception e){
          e.printStackTrace();
      }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원수정에 실패하였습니다."));
    }

    //회원탈퇴
    @DeleteMapping("/{userId}")
    public ResponseEntity<? extends BaseResponseBody> deleteUser(
            @PathVariable int userId) {

        boolean flg = userService.deleteUser(userId);
        try{
            if(flg)  return ResponseEntity.status(200).body(BaseResponseBody.of(200, "탈퇴가 완료되었습니다."));
            else return ResponseEntity.status(404).body(BaseResponseBody.of(404, "탈퇴할 회원이 존재하지 않습니다."));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원탈퇴에 실패하였습니다."));
    }


}

