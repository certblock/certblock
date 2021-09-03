# Sub-1 : 기능 명세
## 가상 머신 구성
- VirtualBox 설치 : https://www.virtualbox.org/wiki/Downloads > Windows hosts(VirtualBox-6.1.26-145957-Win)
- Vagrant 설치 : https://www.vagrantup.com/downloads > WINDOWS BINARY DOWNLOAD(vagrant_2.2.18_x86_64)<br>
명령 프롬프트 창에서 버전 확인 > C:\Users\multicampus>vagrant version<br>
호스트와 가상 머신 간 파일 전송 플러그인 설치 > C:\Users\multicampus>vagrant plugin install vagrant-scp<br>
원하는 작업 디렉토리에서 Vagrant 초기화(설정 파일 생성) > C:\Users\multicampus\Documents\blockchain\vagrant>vagrant init<br>
생성된 Vagrantfile의 내용 수정 > 파일 참조<br>
가상 머신 구동 명령어 실행 > C:\Users\multicampus\Documents\blockchain\vagrant>vagrant up<br>
## 이더리움 eth0 노드 구성
- 가상 머신 접속(eth0) > C:\Users\multicampus\Documents\blockchain\vagrant>vagrant ssh eth0
- geth 설치<br>
vagrant@eth0:~$ sudo apt-get update<br>
vagrant@eth0:~$ sudo apt-get install software-properties-common<br>
vagrant@eth0:~$ sudo add-apt-repository -y ppa:ethereum/ethereum<br>
vagrant@eth0:~$ sudo apt-get install ethereum<br>
vagrant@eth0:~$ geth version > Geth Version: 1.10.8-stable<br>
- Datadir 생성<br>
vagrant@eth0:~$ mkdir -p ./dev/eth_localdata<br>
- genesis.json 생성<br>
vagrant@eth0:~$ cd dev/eth_localdata/<br>
vagrant@eth0:~/dev/eth_localdata$ vi genesis.json > 파일 참조<br>
- geth에 datadir경로와 genesis설정 적용<br>
vagrant@eth0:~/dev/eth_localdata$ geth --datadir ~/dev/eth_localdata init ~/dev/eth_localdata/genesis.json<br>
- geth 설정을 적용한 실행<br>
vagrant@eth0:~$ geth --networkid 921 --datadir ~/dev/eth_localdata --nodiscover --port 30303 --rpc --rpcport "8545" --maxpeers 2 --rpcaddr "0.0.0.0" --rpccorsdomain "*" --rpcapi "eth, net, web3, miner, debug, personal, rpc" --allow-insecure-unlock console<br>
## 이더리움 eth1 노드 구성
- 가상 머신 접속(eth1) > C:\Users\multicampus\Documents\blockchain\vagrant>vagrant ssh eth1<br>
- geth 설치 ~ datadir경로와 genesis설정 적용 > eth0과 동일<br>
- geth 설정을 적용한 실행<br>
vagrant@eth1:~$ geth --datadir ~/dev/eth_localdata --networkid 921 --http --http.port "8545" --port "30303" --maxpeers 2 --allow-insecure-unlock console<br>
## 이더리움 계정 생성
- geth 실행 후 계정 생성<br>
personal.newAccount("ssafy")<br>
- keystore에서 파일 확인<br>
vagrant@eth0:~$ cd dev/eth_localdata/keystore<br>
vagrant@eth0:~/dev/eth_localdata/keystore$ ls<br>
UTC--2021-09-02T07-18-07.485430112Z--faed3b23ab96e9b6fb568e2617a6abc43967c730<br>
UTC--2021-09-02T07-18-18.591875534Z--58d08a3b93c9703492e31776e07cf4144686adb5<br>
## 코인베이스(Coinbase) 설정
- 맨 처음 생성되는 이더리움 계정이 자동으로 coinbase로 지정됨<br>
> eth.coinbase<br>
"0xfaed3b23ab96e9b6fb568e2617a6abc43967c730"<br>
## 마이닝(Mining) 시작
- > miner.start(1)<br>
- 마이닝 진행 상태 확인<br>
> eth.mining<br>
true<br>
- 마이닝 중단<br>
> miner.stop()<br>
null<br>
## 마이닝(Mining) 결과 확인
- 계정별 잔액 확인<br>
> eth.getBalance(eth.accounts[0])<br>
810000000000000000000<br>
> eth.getBalance(eth.accounts[1])<br>
0<br>
- 생성된 블록 수 조회<br>
> eth.blockNumber<br>
162<br>
- 블록의 상세 정보 조회<br>
> eth.getBlock(162)<br>
{<br>
  difficulty: 140207,<br>
  extraData: "0xd883010a08846765746888676f312e31362e34856c696e7578",<br>
  gasLimit: 137483795,<br>
  gasUsed: 0,<br>
  hash: "0xec2b93738bef81002af5aaffbece827272f23c5814cf9d37e65e654281837603",<br>
  logsBloom: "0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",<br>
  miner: "0xfaed3b23ab96e9b6fb568e2617a6abc43967c730",<br>
  mixHash: "0x88b53437b54650a705fcda723e1751e38ee30af7f24ce7a172e2d174dbcfbb0a",<br>
  nonce: "0x598e13e810e2a554",<br>
  number: 162,<br>
  parentHash: "0x5fa2cabc543241757659f2c4ded41d966d6446abdf3f83911bce8570a7018038",<br>
  receiptsRoot: "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",<br>
  sha3Uncles: "0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347",<br>
  size: 538,<br>
  stateRoot: "0xf395edadea5f59c0b845e63ca865cf1db6d0cf1b506ec2c08025c48c3b338bb7",<br>
  timestamp: 1630636196,<br>
  totalDifficulty: 21872893,<br>
  transactions: [],<br>
  transactionsRoot: "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",<br>
  uncles: []<br>
}<br>
