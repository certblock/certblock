create table User
(
    id            int auto_increment
        primary key,
    address       varchar(255)                        null,
    birth         datetime                            null,
    date          timestamp default CURRENT_TIMESTAMP not null,
    email         varchar(255)                        null,
    name          varchar(255)                        null,
    password      varchar(255)                        null,
    phone         varchar(255)                        null,
    type          int                                 not null,
    walletAddress varchar(255)                        null
);

INSERT INTO certblockDB.User (id, address, birth, date, email, name, password, phone, type, walletAddress) VALUES (2, null, '2021-09-22 09:00:00', '2021-09-30 17:58:39', 'ssafy@ssafy.com', '싸피대학교', 'ssafy', '123', 1, '0x3861ce91ED1b8dE311F025923F711EA07a8501c1');
INSERT INTO certblockDB.User (id, address, birth, date, email, name, password, phone, type, walletAddress) VALUES (3, null, '2021-09-17 09:00:00', '2021-09-30 17:58:59', 'java@java.com', '자바대학교', 'java', '321', 1, null);
INSERT INTO certblockDB.User (id, address, birth, date, email, name, password, phone, type, walletAddress) VALUES (4, null, '2021-09-27 09:00:00', '2021-09-30 17:59:15', 'vue@vue.com', '뷰대학교', 'vue', '123321', 1, null);
INSERT INTO certblockDB.User (id, address, birth, date, email, name, password, phone, type, walletAddress) VALUES (10, null, '2021-10-13 09:00:00', '2021-10-04 15:47:53', 'kimssafy@naver.com', '김싸피', 'kimssafy', '01012345678', 2, '0xaaee906ae0d5d48eef36188d859aca2cd68ffe7a');
INSERT INTO certblockDB.User (id, address, birth, date, email, name, password, phone, type, walletAddress) VALUES (20, null, '1980-02-08 09:00:00', '2021-10-07 11:29:35', 'ssafy@sfy.com', 'SSAFY소프트웨어', 'ssafy1234!', '01030187019', 3, '0x35ee485229c54726fccec07a86c5f2be41725a5e');

create table Student
(
    id int not null
        primary key,
    constraint FKkfqq4nickg8wu56axc7jktv1
        foreign key (id) references User (id)
);

INSERT INTO certblockDB.Student (id) VALUES (10);


create table University
(
    apiServerBaseUrl varchar(255) null,
    id               int          not null
        primary key,
    constraint FKa6o79d938ms0ell9hsuds6shf
        foreign key (id) references User (id)
);

INSERT INTO certblockDB.University (apiServerBaseUrl, id) VALUES ('https://j5a507.p.ssafy.io/api/ssafyuniv', 2);
INSERT INTO certblockDB.University (apiServerBaseUrl, id) VALUES ('https://j5a507.p.ssafy.io/api/ssafyuniv', 3);
INSERT INTO certblockDB.University (apiServerBaseUrl, id) VALUES ('https://j5a507.p.ssafy.io/api/ssafyuniv', 4);


create table Company
(
    id int not null
        primary key,
    constraint FK2cua60pupg0veb3nuy28vb45e
        foreign key (id) references User (id)
);

INSERT INTO certblockDB.Company (id) VALUES (20);


create table UniversityStudent
(
    student_id      int not null,
    university_id   int not null,
    studentIdInUniv int null,
    type            int not null,
    primary key (student_id, university_id),
    constraint FKe28x2af7pvukktpauj9cvjldd
        foreign key (student_id) references Student (id),
    constraint FKhu1et506uc516n3ptsgp78ysl
        foreign key (university_id) references University (id)
);

INSERT INTO certblockDB.UniversityStudent (student_id, university_id, studentIdInUniv, type) VALUES (10, 2, 20201234, 1);
INSERT INTO certblockDB.UniversityStudent (student_id, university_id, studentIdInUniv, type) VALUES (10, 3, 20201234, 2);
INSERT INTO certblockDB.UniversityStudent (student_id, university_id, studentIdInUniv, type) VALUES (10, 4, 20201234, 3);



create table Certificate
(
    id            int auto_increment
        primary key,
    date          timestamp default CURRENT_TIMESTAMP not null,
    hash          varchar(255)                        null,
    issuance      bit                                 null,
    type          int                                 not null,
    student_id    int                                 null,
    university_id int                                 null,
    constraint FKib76k3p3b4974tjk6yp149qxd
        foreign key (student_id, university_id) references UniversityStudent (student_id, university_id)
);

INSERT INTO certblockDB.Certificate (id, date, hash, issuance, type, student_id, university_id, expiryDate, issuanceDate) VALUES (1, '2021-10-06 15:49:01', null, true, 1, 10, 2, '2022-10-07 22:00:07', '2021-10-07 22:00:07');
INSERT INTO certblockDB.Certificate (id, date, hash, issuance, type, student_id, university_id, expiryDate, issuanceDate) VALUES (2, '2021-10-06 15:49:01', null, true, 2, 10, 2, '2022-10-07 22:00:07', '2021-10-07 22:00:08');
INSERT INTO certblockDB.Certificate (id, date, hash, issuance, type, student_id, university_id, expiryDate, issuanceDate) VALUES (3, '2021-10-07 20:06:16', null, false, 3, 10, 3, null, null);
INSERT INTO certblockDB.Certificate (id, date, hash, issuance, type, student_id, university_id, expiryDate, issuanceDate) VALUES (4, '2021-10-07 20:06:16', null, false, 4, 10, 3, null, null);
INSERT INTO certblockDB.Certificate (id, date, hash, issuance, type, student_id, university_id, expiryDate, issuanceDate) VALUES (5, '2021-10-04 15:50:06', null, false, 5, 10, 4, null, null);
INSERT INTO certblockDB.Certificate (id, date, hash, issuance, type, student_id, university_id, expiryDate, issuanceDate) VALUES (6, '2021-10-04 15:50:06', null, false, 6, 10, 4, null, null);




create table Submit
(
    id             int auto_increment
        primary key,
    date           timestamp default CURRENT_TIMESTAMP not null,
    certificate_id int                                 null,
    company_id     int                                 null,
    constraint FK6m8l9qumksvbjvhrsbch2by5u
        foreign key (company_id) references Company (id),
    constraint FKggtvnifsg3d0v3el647tprw3e
        foreign key (certificate_id) references Certificate (id)
);



create table SsafyunivStudent
(
    id    int auto_increment
        primary key,
    path1 varchar(255) null,
    path2 varchar(255) null,
    type  int          not null
);

INSERT INTO certblockDB.SsafyunivStudent (id, path1, path2, type) VALUES (20201234, 'https://j5a507.p.ssafy.io/static/20201234-1.png', 'https://j5a507.p.ssafy.io/static/20201234-2.jpeg', 1);

