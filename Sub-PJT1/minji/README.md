# 1. 프라이빗 이더리움 네트워크 구축


# 1-1 가상머신 구성


# VirtualBox 설치


https://www.virtualbox.org/wiki/Downloads
VirtualBox 6.1.26 platform packages (Windows hosts)
설치 파일 실행
Custom Setup의 기본값 그대로 설치 진행


# Vagrant 설치


https://www.vagrantup.com/downloads
WINDOWS BINARY DOWNLOAD Vagrant 2.2.18 (64-bit)
설치 파일 실행
설치 여부 및 버전 확인
vagrant version
호스트와 가상 머신 간 파일 전송 플러그인 설치
vagrant plugin install vagrant-scp


# 가상 머신 생성 및 구동


- 작업 디렉토리 생성 및 Vagrant 설정 파일 생성

mkdir C:\Users\multicampus\be-ethereum
cd C:\Users\multicampus\be-ethereum
vagrant init


- Vagrantfile 수정


VAGRANT_API_VERSION = "2"

vms = {
'eth0' => '10',
'eth1' => '11'
}

Vagrant.configure(VAGRANT_API_VERSION) do |config|
config.vm.box = "ubuntu/bionic64"
vms.each do |key, value|
    config.vm.define "#{key}" do |node|
    node.vm.network "private_network", ip: "192.168.50.#{value}"
    if "#{key}" == "eth0"
        node.vm.network "forwarded_port", guest: 8545, host: 8545
    end
    node.vm.hostname = "#{key}"
    node.vm.provider "virtualbox" do |nodev|
        nodev.memory = 2048
    end
    end
end
end


- 가상 머신 구동 명령어 실행

vagrant up


- 가상 머신 구동 상태 확인

vagrant status


- 가상 머신 접속

vagrant ssh [eth0 / eth1]


# 1-2 이더리움 eth0/eth1 노드 구성


# Geth 설치
// eth0 가상머신과 eth1 가상머신에서 각각 수행

sudo apt-get update
sudo apt-get install software-properties-common
sudo add-apt-repository -y ppa:ethereum/ethereum
sudo apt-get install ethereum


// 설치 확인
geth version


# 디렉토리 생성 및 genesis 블록파일 생성(eth0)
mkdir -p dev/eth_localdata
cd dev/eth_localdata

vi CustomGenesis.json


# CustomGenesis.json
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
- config
제네시스 블록의 설정값 정의
- chainId
블록체인을 식별하는 정숫값을 입력
값이 비어있으면 안 됨
- homesteadBlock
홈스테드를 적용하는 하드 포크 블록 번호
제네시스 블록은 0을 설정
- eip150Block,eip155Block, eip158Block
이더리움 개선 제안(EIPs)를 적용한 하드 포크 블록 번호
제네시스 블록은 0을 설정
- nonce
mixHash와 함께 해당 블록에 충분한 양의 작업 증명 연산을 실행했음을 증명하는 값
- gasLimit
해당 블록에서 사용 가능한 가스의 최대 크기
- difficulty
블록 생성 난이도
높을 수록 블록 생성 속도 저하
- coinbase
블록 생성에 따른 보상금 지급 계정 주소
- alloc
제네시스 블록을 생성할 때 특정 계정에 미리 정해진 액수의 이더를 지급해 블록 생성 가능


# eth0 노드 설정
geth --networkid 921 --maxpeers 2 --datadir ~/dev/eth_localdata --allow-insecure-unlock --port 30303 --rpc --rpcport 8545 --rpcaddr 0.0.0.0 --rpccorsdomain "*" --rpcapi "admin,net,miner,eth,rpc,web3,txpool,debug,personal" --miner.threads 1
geth attach http://0.0.0.0:8545


# eth1 노드 설정
geth --networkid 921 --maxpeers 2 --datadir ~/dev/eth_localdata --allow-insecure-unlock --port 30303 --rpc --rpcport 8545 --rpcaddr localhost --rpccorsdomain "\*" --miner.threads 1
geth attach http://localhost:8545
