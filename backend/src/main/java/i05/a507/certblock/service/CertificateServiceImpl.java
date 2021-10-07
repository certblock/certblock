package i05.a507.certblock.service;

import i05.a507.certblock.ContractService;
import i05.a507.certblock.domain.Certificate;
import i05.a507.certblock.domain.Student;
import i05.a507.certblock.domain.University;
import i05.a507.certblock.domain.UniversityStudent;
import i05.a507.certblock.dto.Student.StudentCertRes;
import i05.a507.certblock.exception.CertificateNotFoundException;
import i05.a507.certblock.repository.CertificateRepository;
import i05.a507.certblock.ssafyuniv.Resp;
import i05.a507.certblock.utils.Base64Encoder;
import i05.a507.certblock.utils.Checksum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

//    private final EthereumService ethereumService;
    private final CertificateRepository certificateRepository;
    private final S3Service s3Service;
//    private final Aes128 aes128;
    private final ContractService contractService;

    @Override
    public Certificate findById(int id) {
        return certificateRepository.findById(id).orElseThrow(CertificateNotFoundException::new);
    }

    @Override
    public StudentCertRes issueCertificate(int id) throws Exception {
        Certificate certificate = findById(id);
        UniversityStudent universityStudent = certificate.getUniversityStudent();
        University university = universityStudent.getUniversity();
        Student student = universityStudent.getStudent();
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

        addCertificateToBlockChain(fileHash, fileName, student.getWalletAddress(), certificate.getType() - 1);


        String[] ret = getCertificateFromBlockChain(student.getWalletAddress(), certificate.getType() - 1);

        // 발급받은 증명서 DB 갱신
        certificate.setIssuance(true);
        certificate.setIssuanceDate(new Date());
        certificate.setExpiryDate(new Date(Long.parseLong(ret[2]) * 1000L));
        certificateRepository.save(certificate);

        return StudentCertRes.of(certificate);
    }

    @Override
    public String retrieveCertificate(int id) throws Exception {
        Certificate certificate = findById(id);

        String[] ret = getCertificateFromBlockChain(certificate.getUniversityStudent().getStudent().getWalletAddress(),
                certificate.getType() - 1);
        String fileHashFromBlockChain = ret[0];
        String fileNameFromBlockChain = ret[1];

        String fileExtName = fileNameFromBlockChain.substring(fileNameFromBlockChain.lastIndexOf(".") + 1);
        File imageFile = s3Service.getFile(fileNameFromBlockChain);
        String fileHash = Checksum.getFileChecksumWithSha256(imageFile);
        String encoded = Base64Encoder.encodeFileToBase64Binary(imageFile, fileExtName);
        if (!fileHash.equals(fileHashFromBlockChain)) {
            throw new RuntimeException("fileHash is not same");
        }

        return encoded;
    }

    public int getOwnerAddress() throws IOException, ExecutionException, InterruptedException {

        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("getOwnerAddress",
                Collections.emptyList(),
                Arrays.asList(new TypeReference<Address>() {
                }));

        // 2. ethereum을 function 변수로 통해 호출
        return ((BigInteger) contractService.ethCall(function)).intValue();
    }

    private void addCertificateToBlockChain(String fileHash, String fileName, String userWalletAddress, int type)
            throws IOException, ExecutionException, InterruptedException {

        List<Type> inputParameters = new ArrayList<>();
        inputParameters.add(new Utf8String(fileHash));
        inputParameters.add(new Utf8String(fileName));
        inputParameters.add(new Address(userWalletAddress));
        inputParameters.add(new Uint16(type));

        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("addCertificate", inputParameters, Collections.emptyList());

        // 2. sendTransaction
        String txHash = contractService.ethSendTransaction(function);

        // 3. getReceipt
        TransactionReceipt receipt = contractService.getReceipt(txHash);
        System.out.println("receipt = " + receipt);
    }


    private String[] getCertificateFromBlockChain(String userWalletAddress, int type)
            throws IOException, ExecutionException, InterruptedException {

        List<Type> inputParameters = new ArrayList<>();
        inputParameters.add(new Address(userWalletAddress));
        inputParameters.add(new Uint16(type));

        List<TypeReference<?>> outputParameters = Arrays.asList(new TypeReference<Utf8String>(){},
                new TypeReference<Utf8String>(){},
                new TypeReference<Uint256>(){});

        // 1. 호출하고자 하는 function 세팅[functionName, parameters]
        Function function = new Function("getCertificate", inputParameters, outputParameters);

        List<Type> ethRes = contractService.ethCall(function);
        String[] ret = new String[3];
        ret[0] = (String) ethRes.get(0).getValue();
        ret[1] = (String) ethRes.get(1).getValue();
        ret[2] = ethRes.get(2).getValue().toString();
        return ret;
    }
}
