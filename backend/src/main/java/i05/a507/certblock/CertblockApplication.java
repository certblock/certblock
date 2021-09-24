package i05.a507.certblock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "domain")
public class CertblockApplication {

  public static void main(String[] args) {
    SpringApplication.run(CertblockApplication.class, args);
  }

}
