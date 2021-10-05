package i05.a507.certblock.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Configuration
public class SmsConfig {
    @Value("${app.sens.access-key}")
    private String accesskey;
    @Value("${app.sens.service-key}")
    private String servicekey;
    @Value("${app.sens.secret-key}")
    private String secretkey;
    @Value("${app.sens.from-num}")
    private String fromnum;

    @Bean
    public String accessAPIKey(){
        return accesskey;
    }

    @Bean
    public String serviceAPIKey(){
        return servicekey;
    }

    @Bean
    public String secretAPIKey(){
        return secretkey;
    }

    @Bean
    public String fromNum(){
        return fromnum;
    }
}
