package i05.a507.certblock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EthereumConfig {

    @Value("${ethereum.ethereumFrom}")
    private String ethereumFrom;

    @Value("${ethereum.contract}")
    private String contract;

    @Value("${ethereum.privateNetworkUrl}")
    private String privateNetworkUrl;

    @Bean
    public String ethereumFrom() { return ethereumFrom; }

    @Bean
    public String contract() { return contract; }

    @Bean
    public String privateNetworkUrl() { return privateNetworkUrl; }
}
