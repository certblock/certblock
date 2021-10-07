package i05.a507.certblock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // DB 자동 갱신
public class JpaConfig {
}

