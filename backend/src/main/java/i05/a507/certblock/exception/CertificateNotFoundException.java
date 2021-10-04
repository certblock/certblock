package i05.a507.certblock.exception;

public class CertificateNotFoundException extends RuntimeException {
    public CertificateNotFoundException() {
        super("증명서를 찾을 수 없습니다.");
    }
}
