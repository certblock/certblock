package i05.a507.certblock.ssafyuniv;

import i05.a507.certblock.dto.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("SsafyunivStudentController")
@RequestMapping("/api/ssafyuniv/api")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;
    @GetMapping("/students/{studentId}/certificates/{type}")
    public ResponseEntity<?> issueCertificate(@PathVariable int studentId, @PathVariable int type) {
        String path = studentService.issueCertificate(studentId, type);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, path));
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable int studentId) {
        Student student = studentService.findById(studentId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(student != null? 200 : 404, ""));
    }
}