package i05.a507.certblock.config;

import i05.a507.certblock.utils.Aes128;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${app.aes128.secret-key}")
    private String secretKey;

    @Bean
    Aes128 aes128() {
        return new Aes128(secretKey);
    };
}
