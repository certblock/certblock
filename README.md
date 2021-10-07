# CertBlock(증명서 발급 서비스)

## 서비스 요약
- 블록체인으로 증명서(성적 증명서, 졸업 증명서 등)를 발급/조회하는 서비스

## 배경
- 자소서 작성할 때 매번 관련 증명서를 첨부해야만 했고, 해당 증명서가 기간이 만료되었을 경우 새로 발급받은 뒤에 사용해야 함
- 면접때 증명서를 챙겨가야 함

## 기대 효과
- 학생
  - 기업 지원 시 증명서를 따로 준비하지 않아도 됨 ( 현재는 오프라인, 온라인 제출 2번 이상 하는데 certblock사용하면 한 번만 하면 끝 )
  - 증명서를 한곳에서 관리 가능 ( 여러 학위가 있을 때 번거롭게 각 발급기관에 방문하지 않아도 됨 )
  - 따로 증명서를 스캔할 필요 없이 기업에 한번에 제출 가능
  - 블록체인에 증명서를 저장하기 때문에 보안성이 뛰어남

- 기업
  - 위변조 불가능한, 신뢰도 있는 증명서 조회 가능
  - 채용과정에서 발생하는 증명서 문서 관리 필요 없음
  - 증명서 발급에 필요한 종이 소모와 같은 불필요한 비용을 절감하고 환경을 보호할 수 있음
- 환경
  - 종이 소모를 줄일 수 있습니다.

## 증명서 목록
- 졸업 증명서
- 성적 증명서

## 확장 가능성
- 다양한 증명서 발급 가능
    - 백신 접종 완료증, 자격증, 어학성적, 수상 관련 증명서

## 기술 스택
* Frontend: Vue.js, Vuetify, HTML/CSS/JavaScript
* BackEnd: Spring Boot 2.5.4, JPA(Hibernate)
* Storage: MySQL 5.7
* Deploy: AWS EC2
* BlockChain : Solidity

## Dockerization 방법
docker buildkit을 위해 `docker/dockerfile:1` 이미지를 미리 pull 합니다.
```sh
docker pull docker/dockerfile:1
```

<br>

backend와 frontend를 dockerizing합니다.
```sh
DOCKER_BUILDKIT=1 docker build -t ${REGISTRY}/certblock/backend:${VERSION} --target prod ./backend
DOCKER_BUILDKIT=1 docker build -t ${REGISTRY}/certblock/nginx:${VERSION} --target prod ./frontend
```


## app 서버 구축 및 app 실행 방법
먼저, Docker와 Docker Compose가 설치되어 있어야 합니다.

<br>

root directory를 생성 및 이동합니다(root directory를 `~/certblock`으로 하겠습니다).
```sh
mkdir ~/certblock
cd ~/certblcok
```

<br>

project의 다음 파일을 root directory로 복사합니다.
- [docker-compose.yml](./docker-compose.yml)
- [init-letsencrypt.sh](./init-letsencrypt.sh)
- [nginx/default.conf.template](nginx/default.conf.template)


<br>

docker compose와 certbot을 실행하기 위해서는 `.env` 파일이 필요합니다. `.env`의 항목은 다음과 같습니다.
```env
REGISTRY=repo.treescale.com
APP_DOMAIN=my-app.com
LETSENCRYPT_EMAIL=example@email.com

# mysql
MYSQL_USER=myuser
MYSQL_PASSWORD=mypassword
MYSQL_ROOT_PASSWORD=myrootpassword
MYSQL_DATABASE=mydb

# backend
SPRING_DATASOURCE_USERNAME=${MYSQL_USER}
SPRING_DATASOURCE_PASSWORD=${MYSQL_PASSWORD}

S3_ACCESS_KEY=1q2w3e4r
S3_SECRET_KEY=1q2w3e4r/1q2w3e4r
S3_BUCKET=s3bucketname
S3_REGION=ap-northeast-2

# NAVER CLOUD PLATFORM - Simple & Easy Notification Service
SENS_ACCESSKEY=123WER
SENS_SERVICEKEY=S1D32FS1DF
SENS_SECRETKEY=nS1D2F3SFDS
SENS_FROMNUM=01012345678

# >= 16
AES128_SECRETKEY=1q2w3e4r1q2w3e4r1q2w3e4r1q2w3e4r

# mysql, backend, nginx
TZ=Asia/Seoul
```

<br>

[init-letsencrypt.sh](./init-letsencrypt.sh)을 실행시켜 certbot에 의해 SSL/TLS 인증서를 생성하도록 해야 합니다.
```sh
sudo ./init-letsencrypt.sh
```

<br>

docker compose를 통해 application을 실행합니다.
```sh
docker-compose up -d
```


## 자동 배포
[auto-deploy.yml](.github/workflows/auto-deploy.yml)에 의해 자동으로 배포가 이루어 집니다.

### github secrets 설정
프로젝트의 `Settings > Secrets`에서 다음 항목에 대한 secrets 등록이 필요합니다.
```env
HOST : 10.11.12.13
REGISTRY : repo.treescale.com
REGISTRY_PASSWORD : registrypass
REGISTRY_USERNAME : registryuser
SSH_PORT : 22
SSH_PRIVATE_KEY : -----BEGIN OPENSSH PRIVATE KEY----- ~~
SSH_USERNAME : sshuser
```

### 과정
- build-images
  - bild images
  - upload artifacts(images) to share between jobs
- deploy
  - download artifacts(built images)
  - login remote registry
  - copy files using ssh(scp)
  - execute docker compose using ssh


## 팀 노션 
- [노션 링크](https://www.notion.so/5-7-b221c8c2fd5c4e5384a3b508dd2c2611)
