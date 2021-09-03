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
