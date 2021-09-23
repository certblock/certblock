package i05.a507.certblock.controller;

import i05.a507.certblock.domain.Company;
import i05.a507.certblock.domain.Student;
import i05.a507.certblock.domain.User;
import i05.a507.certblock.dto.BaseResponseBody;
import i05.a507.certblock.dto.User.UserLoginReq;
import i05.a507.certblock.dto.User.UserLoginRes;
import i05.a507.certblock.dto.User.UserModifyReq;
import i05.a507.certblock.dto.User.UserRegisterReq;
import i05.a507.certblock.service.CompanyService;
import i05.a507.certblock.service.StudentService;
import i05.a507.certblock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    CompanyService companyService;

    //회원가입
    @PostMapping("/")
    public ResponseEntity<? extends BaseResponseBody> registUser(
            @RequestBody UserRegisterReq userRegisterReq){

        try {
            userService.registUser(userRegisterReq);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(400, "회원 가입에 실패했습니다."));
    }

    //유저 목록 조회
    @GetMapping("/")
    public ResponseEntity<?> selectAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.status(200).body(userList);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginReq userLoginReq){

       User user = userService.loginUser(userLoginReq);
        if(user != null){
            UserLoginRes userLoginRes = new UserLoginRes();
            userLoginRes.setUserId(user.getUserId());
            userLoginRes.setEmail(user.getEmail());
            userLoginRes.setName(user.getName());
            userLoginRes.setPhone(user.getPhone());
            userLoginRes.setBirth(user.getBirth());
            userLoginRes.setType(user.getType());

            int type = user.getType();
            if(type==2) {
               Student student =  studentService.getStudent(user.getUserId());
               userLoginRes.setStudentId(student.getStudentId());
            }else{
                Company company =  companyService.getCompany((user.getUserId()));
                userLoginRes.setCompanyId(company.getCompanyId());
            }

            return ResponseEntity.status(200).body(userLoginRes);
        }
        else{
            return ResponseEntity.status(200).body(BaseResponseBody.of(404, "존재하지 않는 회원입니다."));
        }

    }

    //특정 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<?> selectUser(@PathVariable int userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.status(200).body(user);
    }

    //회원 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<? extends BaseResponseBody> modifyUser(
            @PathVariable int userId, @RequestBody UserModifyReq userModifyReq) {

      boolean flg = userService.modifyUser(userId,userModifyReq);
      if(flg)  return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원정보 수정이 완료되었습니다."));
      else return ResponseEntity.status(200).body(BaseResponseBody.of(404, "수정할 회원이 존재하지 않습니다."));
    }

    //회원탈퇴
    @DeleteMapping("/{userId}")
    public ResponseEntity<? extends BaseResponseBody> deleteUser(
            @PathVariable int userId) {

        boolean flg = userService.deleteUser(userId);
        if(flg)  return ResponseEntity.status(200).body(BaseResponseBody.of(200, "탈퇴가 완료되었습니다."));
        else return ResponseEntity.status(200).body(BaseResponseBody.of(404, "탈퇴할 회원이 존재하지 않습니다."));
    }


}

