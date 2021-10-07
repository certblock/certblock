package i05.a507.certblock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class ContractService {

    private String from = "0x3861ce91ED1b8dE311F025923F711EA07a8501c1";

    private String contract = "0x41fF1914b275D06E07f459e9Dd1cA7Ff54F0A580";

    // hardcording because of testing
    private String pwd = "0xaca10c42b1fe8262a88f696e5c389a895f1091e8bec36a3f41a8fbd47d3f1f1b";

    private Admin web3j = null;

    public ContractService() {
        web3j = Admin.build(new HttpService("http://13.125.144.163:7540"));
    }

    public List<Type> ethCall(Function function) throws IOException {
        //1. transaction 제작
        Transaction transaction = Transaction.createEthCallTransaction(from, contract,
                FunctionEncoder.encode(function));

        //2. ethereum 호출후 결과 가져오기
        EthCall ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

        //3. 결과값 decode
        List<Type> decode = FunctionReturnDecoder.decode(ethCall.getResult(),
                function.getOutputParameters());

//        System.out.println("ethCall.getResult() = " + ethCall.getResult());
//        System.out.println("getValue = " + decode.get(0).getValue());
//        System.out.println("getType = " + decode.get(0).getTypeAsString());

        return decode;
//        return decode.get(0).getValue();
    }

    public String ethSendTransaction(Function function)
            throws IOException, InterruptedException, ExecutionException {

        // 1. account에 대한 nonce값 가져오기.
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                from, DefaultBlockParameterName.LATEST).sendAsync().get();

        EthGetBalance ethGetBalance = web3j.ethGetBalance(from, DefaultBlockParameterName.LATEST)
                .sendAsync()
                .get();
        BigInteger wei = ethGetBalance.getBalance();

        // 2. Account Lock 해제
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from, pwd).send();

        if (personalUnlockAccount.accountUnlocked()) { // unlock 일때

            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            // 3. Transaction 값 제작
            Transaction transaction = Transaction.createFunctionCallTransaction(from, nonce, Transaction.DEFAULT_GAS,
                    BigInteger.valueOf(1000000L), contract, FunctionEncoder.encode(function));

            // 4. ethereum Call &
//            EthSendTransaction ethSendTransactionResponse = web3j.ethSendTransaction(transaction).send();

            // 5. transaction에 대한 transaction Hash값 얻기.
//            String transactionHash = ethSendTransactionResponse.getTransactionHash();

            // ledger에 쓰여지기 까지 기다리기.
//            Thread.sleep(5000);

//            return transactionHash;


            EthSendTransaction transactionResponse = web3j.ethSendTransaction(transaction).send();
            if (transactionResponse.hasError()) {
                String message = transactionResponse.getError().getMessage();
                log.error("transaction failed , info : {}", message);
                return message;
            }else{
                String hash = transactionResponse.getTransactionHash();
                return hash;
            }
        } else {
            throw new PersonalLockException("check ethereum personal Lock");
        }
    }

    public TransactionReceipt getReceipt(String transactionHash) throws IOException {

        EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();

        if (transactionReceipt.getTransactionReceipt().isPresent()) {
            System.out.println("transactionReceipt.getResult().getContractAddress() = " +
                    transactionReceipt.getResult());
        } else {
            System.out.println("transaction complete not yet");
        }

        return transactionReceipt.getResult();
    }

    private class PersonalLockException extends RuntimeException {
        public PersonalLockException(String msg) {
            super(msg);
        }
    }

}
