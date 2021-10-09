package i05.a507.certblock.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Submit {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="certificate_id")
    private Certificate certificate;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @CreatedDate
    private LocalDateTime createdTime;

    private LocalDateTime expiryDate;

    private int noticeId;
    private String noticeTitle;

    public Submit(Certificate certificate, Company company, LocalDateTime expiryDate, int noticeId, String noticeTitle) {
        this.certificate = certificate;
        this.company = company;
        this.expiryDate = expiryDate;
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
    }

    public Submit() {

    }
}
