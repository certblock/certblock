package i05.a507.certblock.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

@Service
public class EthereumService {

    private final String privateKey = "a7ef9ab0de6aa9049487373b7701b50ebf3ab15d9e8bcbe0aa91c8bd10ffc83f";
    private final String ethereumFrom;
    private final String contract;
    private final String privateNetworkUrl;
    private final Admin web3j;

    public EthereumService(@Value("${ethereum.ethereumFrom}") String ethereumFrom,
                           @Value("${ethereum.contract}") String contract,
                           @Value("${ethereum.privateNetworkUrl}") String privateNetworkUrl) {
        this.ethereumFrom = ethereumFrom;
        this.contract = contract;
        this.privateNetworkUrl = privateNetworkUrl;
        this.web3j = Admin.build(new HttpService(privateNetworkUrl));
    }

    public String test() throws IOException {
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        System.out.println(clientVersion);
        return clientVersion;
    }

}
