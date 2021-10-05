package i05.a507.certblock.service;

import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.Student;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.domain.UniversityStudent;
import i05.a507.certblock.exception.CertificateNotFoundException;
import i05.a507.certblock.repository.CertificateRepository;
import i05.a507.certblock.ssafyuniv.Resp;
import i05.a507.certblock.utils.Aes128;
import i05.a507.certblock.utils.Base64Encoder;
import i05.a507.certblock.utils.Checksum;
import lombok.RequiredArgsConstructor;
import net.minidev.json.parser.ParseException;
import org.apache.tomcat.jni.Time;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    final CertificateRepository certificateRepository;
    final S3Service s3Service;
    final Aes128 aes128;

    @Override
    public Certificate findById(int id) {
        return certificateRepository.findById(id).orElseThrow(CertificateNotFoundException::new);
    }

    @Override
    public Certificate issueCertificate(int id) throws Exception {
        Certificate certificate = findById(id);
        UniversityStudent universityStudent = certificate.getUniversityStudent();
        University university = universityStudent.getUniversity();
        WebClient client = WebClient
                .builder()
                .baseUrl(university.getApiServerBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        WebClient.RequestHeadersSpec<?> req = client.get()
                .uri("/api/students/{studentId}/certificates/{certId}", universityStudent.getStudentIdInUniv(), id);
        Resp response = req.retrieve().bodyToMono(Resp.class).block();
        String certImageFilePath = response.getMessage();

        InputStream in = new URL(certImageFilePath).openStream();
        int extensionIdx = certImageFilePath.lastIndexOf('.');
        File file = File.createTempFile("s3file", certImageFilePath.substring(extensionIdx));
        Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // file을 hash한 결괏값
        String fileHash = Checksum.getFileChecksumWithSha256(file);
        String fileName = s3Service.upload(file, "static");


        return certificate;
    }

    @Override
    public String retrieveCertificate(int id) throws Exception {
        Certificate certificate = findById(id);

        String fileNameFromBlockChain = "static/8dd56691-ed73-455a-b8ab-9155fe75dfde.jpeg";
        String fileHashFromBlockChain = "";


        String fileExtName = fileNameFromBlockChain.substring(fileNameFromBlockChain.lastIndexOf(".") + 1);
        File imageFile = s3Service.getFile(fileNameFromBlockChain);
        String fileHash = Checksum.getFileChecksumWithSha256(imageFile);
        String encoded = Base64Encoder.encodeFileToBase64Binary(imageFile, fileExtName);
//        if (!blockChainFileHash.equals(fileHash)) {
//            // TODO: exception
//            return "";
//        }

        return encoded;
    }
}
