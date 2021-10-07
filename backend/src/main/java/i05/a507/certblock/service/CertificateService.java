package i05.a507.certblock.service;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.dto.Student.StudentCertRes;
import net.minidev.json.parser.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public interface CertificateService {
    Certificate findById(int id);
    StudentCertRes issueCertificate(int id) throws Exception;
    String retrieveCertificate(int id) throws Exception;
}
