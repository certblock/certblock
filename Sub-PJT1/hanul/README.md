## 블록체인(Blockchain)

블록체인이란 P2P(Peer to Peer) 네트워크를 통해서 관리되는 분산 데이터베이스의 한 형태로, 거래내역 장부를 중앙 서버 한 곳에 저장하는 것이 아니라 블록체인 네트워크에 연결된 여러 컴퓨터에 저장 및 보관하는 기술을 말한다. 

### 특징

- 안정성
  - 분산형 네트워크 구조로 단일 실패점이 존재하지 않는다. 
  - 일부 시스템에 오류 또는 성능 저하가 발생해도 전체 네트워크에 큰 영향을 주지 않는다.
- 보안성
  - 거래내역 장부는 네트워크 참여자 모두가 공동 소유하여 거래 데이터 조작 방지, 무결성을 보장한다.
- 분산성
  - 중앙 집중형의 시스템을 운영하고 유지보수 하는데 필요한 비용을 절감할 수 있다.
- 확장성
  - 네트워크에 참여하는 누구나 구축, 연결, 확장이 가능하다.
- 투명성
  - 모든 거래 기록을 공개적으로 접근이 가능하다.
  - 비용 절감 가능하다. 비효율적인 관리체계가 필요없어 중간 수수료가 사라지기 때문

### 장점

- 보안성 향상
  - 암호화된 데이터와 키 값으로만 거래가 진행되므로 보안성이 높다.
  - 블록은 최초 블록과 연결 되어있고 참여 노드가 분산 저장되어 데이터 변조, 탈취가 불가능하다.
- 비용 감소
  - 집중화된 중앙 서버와 시스템이 필요 없어 비용이 감소, 보안성 향상으로 보안에 필요한 비용 감소
- 거래속도 향상
  - 거래에 관련된 여러 인증, 증명에 필요한 제3자를 배제하기 때문에 시스템 처리 속도가 향상 된다.



### 블록체인의 종류

- 퍼블릭 블록체인(Public Blockchain)
  - 통상 블록체인이라 하면 퍼블릭 블록체인을 지칭한다.
  - 개방형 블록체인으로 누구나 참여할 수 있고 모든 참여자의 상호 검증을 거쳐 신뢰도가 높다.
  - 모든 참여자의 거래 기록을 남기고, 이를 공유하느라 처리 속도가 느리다.
- 프라이빗 블록체인(Private Blockchain)
  - 폐쇄형 블록체인으로 서비스 제공자(기업 또는 기관)의 승인을 받아야만 참여할 수 있으며 주로 기업에서 활용하여 엔터프라이즈 블록체인이라고도 한다.
  - 한 중앙 기관이 모든 권한을 보유한다.



## 이더리움(Ethereum)

이더리움은 탈중앙화된 연산 플랫폼이다. 다시 말해 개발자들이 탈중앙화된 애플리케이션을 구축하고 배포할 수 있도록 하는 공개, 오픈 소스, 블록체인 기반의 분산 소프트웨어 플랫폼(탈중앙화 앱스토어로서 뿐만 아니라 탈중앙화 인터넷으로서의 역할도 하는)이다.

이더리움의 화폐 단위는 `이더(ETH)`로 표시한다.



## 스마트 컨트랙트

서면으로 이루어지던 계약을 코드로 구현하고 특정 조건이 충족되었을 때 해당 계약이 이행되게 하는 script를 말한다.

### 특징

- 누구나 컨트랙트를 배포할 수 있다.
- 중개인이 없이도 계약을 이행할 수 있다.
  - 두 당사자가 서로를 모르거나 신뢰하지 않더라도 특정 조건이 충족되지 않으면 해당 계약은 실행되지 않기 때문이다.
- 전용 언어(Solidity, Vyper)를 사용하여 논리적인 계약의 작동을 정의할 수 있다. 이후, 논리적인 계약의 작동을 바이트 코드로 변환하여 블록체인에 배포하게 된다.
- 컨트랙트의 소유자가 아니더라도 누구든지 검증할 수 있다.
- 코드의 실행을 자동화할 수 있다.
- 위변조가 어렵다.

### 작동 방식

1. 스마트 컨트랙트로 구현하고자 하는 내용을 Solidity 등으로 구현
2. Solidity 코드를 컴파일 하여 네트워크에 배포할 수 있는 Bytecode를 생성
3. Transaction에 Bytecode를 담고, 마이너가 해당 Transaction이 담긴 블록을 채굴한다. 이와 동시에 해당 Transaction은 블록체인 네트워크에 기록
4. 유저는 ABI(Application Binary Interface)를 통해 배포된 스마트 컨트랙트 코드에 정의된 함수를 호출하는 Bytecode를 생성하고, Transaction에 담아 블록체인 네트워크에 전달
5. 채굴자는 유저로부터 받은 Bytecode를 배포된 스마트 컨트랙트 코드에 따라 EVM(Ethereum Virtual Machine) 위에서 실행한다. 이때, Gas Fee가 계산되면서 블록에 추가되고, 실행 결과가 유효한 경우 실행 결과가 State에 반영된다.

### 스마트 컨트랙트의 한계점

- 블록체인 외부의 정보를 스스로 가져오지 못한다. 즉, 어떠한 조건 충족 여부 확인을 위해 특정 정보가 필요한 경우 문제가 발생한다.
  - 외부 데이터를 가져오는 과정에서 잘못된 데이터를 가져오거나, 고의로 잘못된 데이터를 입력하는 문제가 발생할 수 있다. 다시 말해서 스마트 컨트랙트가 실행되기 위해서는 특정 조건에 대한 정보를 가져와야하고, 이때 필요한 데이터를 가져오기 위해서 제3자에 의존해야 한다면 또 다른 신뢰 이슈가 발생한다.
- 배포 이후에 작동을 수정할 수 없다.
  - 해킹이나 영구적인 사용 불능 사례가 발생하기도 함



## VirtualBox & Vagrant

### VirtualBox

VirtualBox는 가상화를 위한 소프트웨어로, 다양한 운영체제에서 사용할 수 있다. 하드웨어 가상화로 분류되고 Host OS 위에 Guest OS를 만들어 사용함으로써 독립적인 환경을 구성할 수 있다. 

### Vagrant

커맨드라인 인터페이스로 가상 머신 기반 개발 환경을 관리하는 도구이다. 간단한 스크립트를 작성하여 VirtualBox 등 다양한 가상 머신을 쉽게 생성, 수정, 삭제, 관리할 수 있다.

- virtualbox & vagrant 설치

```
brew install --cask virtualbox vagrant vagrant-manager
```

- 호스트와 가상 머신 간 파일 전송 플러그인 설치

```
vagrant plugin install vagrant-scp
```

- 가상 머신 구동 명령어 실행

```
vagrant up
```

- 가상 머신 구동 상태 확인

```
vagrant status
```

- 가상 머신 접속 (eth0 기준)

```
vagrant ssh eth0
```

## 이더리움 eth0/eth1 노드 구성

### 1. Geth 설치

**eth0 가상머신, eth1 가상머신에서 각각 수행**

```
sudo apt-get update
sudo apt-get install software-properties-common
sudo add-apt-repository -y ppa:ethereum/ethereum
sudo apt-get install ethereum
```

### 2. 디렉토리 생성 및 genesis 블록파일 생성

```
mkdir -p dev/eth_localdata
cd dev/eth_localdata

vi genesis.json
```

### 3. genesis.json

제네시스 블록은 블록체인 네트워크의 `첫번째 블록` 이며 수많은 블록체인 네트워크들을 분별하는 하나의 `key` 역할을 한다.

따라서 같은 블록체인 네트워크를 연결하려면 이 파일은 무조건 같아야하며, 이 제네시스 파일의 `chainId` 값을 가지고 구분한다고 생각하면 된다.

```json
{
    "config": {
        "chainId": 921,
        "homesteadBlock": 0,
        "eip150Block": 0,
        "eip155Block": 0,
        "eip158Block": 0
    },
    "difficulty": "0x10",
    "coinbase": "0x0000000000000000000000000000000000000000",
    "gasLimit": "9999999",
    "alloc": {},
    "extraData": "",
    "nonce": "0xdeadbeefdeadbeef",
    "mixhash": "0x0000000000000000000000000000000000000000000000000000000000000000",
    "parentHash": "0x0000000000000000000000000000000000000000000000000000000000000000",
    "timestamp": "0x00"
}
```

- config
  - 제네시스 블록의 설정값 정의
- chainId
  - 블록체인을 식별하는 정숫값을 입력
  - 값이 비어있으면 안 됨
- homesteadBlock
  - 홈스테드를 적용하는 하드 포크 블록 번호
  - 제네시스 블록은 0을 설정
- eip150Block,eip155Block, eip158Block
  - 이더리움 개선 제안(EIPs)를 적용한 하드 포크 블록 번호
  - 제네시스 블록은 0을 설정
- nonce
  - mixHash와 함께 해당 블록에 충분한 양의 작업 증명 연산을 실행했음을 증명하는 값
- gasLimit
  - 해당 블록에서 사용 가능한 가스의 최대 크기
- difficulty
  - 블록 생성 난이도
  - 높을 수록 블록 생성 속도 저하
- coinbase
  - 블록 생성에 따른 보상금 지급 계정 주소
- alloc
  - 제네시스 블록을 생성할 때 특정 계정에 미리 정해진 액수의 이더를 지급해 블록 생성 가능

### 4. 노드 설정

- 노드 생성

```
geth --datadir ~/dev/eth_localdata init genesis.json
```

- 노드의 블록체인 네트워크로 접속

```
geth --datadir ~/dev/eth_localdata --nodiscover --networkid 921 console
```



### 노드 연결

```
// 첫 번째 노드
$ geth --networkid 921 --datadir ~dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8545" --rpcaddr "0.0.0.0" --rpccorsdomain "*" --rpcapi "eth, net, web3, miner, debug, personal, rpc" console
```

```
// 두 번째 노드
$ geth --networkid 921 --datadir ~dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8546" --rpcaddr "0.0.0.0" --rpccorsdomain "*" --rpcapi "eth, net, web3, miner, debug, personal, rpc" console
```

- --rpc
  - rpc통신을 사용한다.

- --rpcport
  - rpc 통신을 위한 port를 8545로 설정한다.

- --rpcaddr
  - rpc 통신에 모든 접근을 허용한다.

- --rpccorsdomain
  - CORS 모두 허용 (와일드카드 *)

- --rpcapi
  - rpc 해당 api를 사용 



### 5. 마이닝 작업

- 계정 생성

```
personal.newAccount("eth")
```

- 계성 확인

```
personal.listWallerts
```

- 마이닝 시작

```
miner.start()
```







## 참고

- https://steemit.com/kr/@sungyu1223/3krc2h
- https://medium.com/haechi-audit-kr/smart-contract-a-to-z-79ebc04d6c86
