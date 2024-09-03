# PTFD
![로고](https://github.com/user-attachments/assets/488e628b-1f33-41dd-86f9-dae5c359f75f)

> 장애인 PT 예약,문의 서비스

> 배포 URL [[PTFD](http://default-front-84485-25569413-20a094b6a545.kr.lb.naverncp.com:30)]

## 프로젝트
- BackEnd 2/ FrontEnd 2
- 개발 기간 2024.06.30 - 2024.09.01
- 장애인 맞춤 PT 어플리케이션은 장애인의 건강과 웰빙을 위한 퍼스널 트레이닝(PT) 예약 및 관리를 간편하게 도와주는 서비스입니다. 이 어플리케이션을 통해 사용자는 편리하게 PT 세션을 예약하고 결제할 수 있으며, 개인의 요구에 맞춘 트레이닝을 경험할 수 있습니다. PT 강사와의 실시간 채팅 기능을 통해 트레이닝 전후로 궁금한 사항을 해결하고, 피드백을 받을 수 있습니다.

## 팀원소개
| **정지현** | **임지혁** | **신민재** | **김민우** |
| ---------- | ---------- | ---------- |------------|
| ![정지현](https://github.com/user-attachments/assets/fafc64d6-822a-40fc-b95a-4ba787bda859) | ![임지혀](https://github.com/user-attachments/assets/7ad081ee-1908-4dd7-9dbc-9d99a9287edb) | ![신민재](https://github.com/user-attachments/assets/b5c36a1c-c2d0-4a34-a40e-ad1b49f402d5) | ![kmw2](https://github.com/user-attachments/assets/da799522-3d1f-4535-8a9c-c398c0b43f38) |
| **백엔드** | **백엔드** | **프론트엔드** | **프론트엔드** |

- 정지현 - 백엔드 개발(Member,Post,Chatting), 어플리케이션 배포, DB 설계
- 임지혁 - 백엔드 개발(Purchase, Post), CI/CD 파이프라인 구축
- 신민재 - 프론트 개발, 웹 디자인
- 김민우 - 프론트 개발


## 개발 환경
- IDE - <img src="https://img.shields.io/badge/intellij-041E42?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/visual studio code-4B8BF5?style=for-the-badge&logo=v&logoColor=black"> <img src="https://img.shields.io/badge/naver cloud-00BC8E?style=for-the-badge&logo=icloud&logoColor=black">
- FrontEnd Skill - <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white"> <img src="https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white">
- BackEnd Skill - <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> <img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/spring cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/stomp-041E42?style=for-the-badge&logo=socketdotio&logoColor=white">
- DataBase - <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/mongoDB-47A248?style=for-the-badge&logo=MongoDB&logoColor=white"> <img src="https://img.shields.io/badge/redis-FF4438?style=for-the-badge&logo=redis&logoColor=white"> 
- Cloud - <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white"> 
- 협업 - <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> 

## 아키텍쳐
![아키텍처](https://github.com/user-attachments/assets/99f25eea-bec4-4140-8b95-ec1b4f83e6ef)

## 서비스 흐름도
![1](https://github.com/user-attachments/assets/25545ad8-bbb2-47cf-82a7-74a8c66a1625)

- ① 장애인 체육 시설 확인 : 로그인 하지 않아도 전국에 있는 장애인 전용 체육 시설의 위치, 
    웹사이트, 번호 등을 카카오맵 화면과 함께 확인할 수 있음
- ② 로그인, 회원가입 : 사용자는 카카오,네이버를 통해 SNS 로그인을 하거나, 회원가입 후 이메일을 통해서 플랫폼에 로그인 할 수 있음.
    일반 회원(장애인 회원), PT 강사 두 가지의 ROLE을 지니며, 강사가 게시한 강좌에 대해 
    장애인 회원은 검색, 좋아요, 결제 등을 진행할 수 있음. 
- ③ 데이터 저장 : 게시물, 사용자 정보, 결제 기록은 분리된 DB에 저장되고 관리 됨.
- ④ 이미지 저장 : 프로필 이미지 혹은 게시물 이미지는 NCP의 Object Storage에 저장 됨.
- ⑤ 실시간 양방향 채팅 : 회원과 강사 간 채팅방을 통하여 양 방향 채팅이 가능 .
- ⑥ PT 예약 & 결제 : PG사를 통해서 강사가 게시한 강좌를 결제하고, 예약할 수 있음.
- ⑦ 결제 푸시 알람 : 카카오톡을 통해 로그인한 회원에게는 카카오톡 메세지로, 다른 
     사용자들에게는 메일로 결제 푸시 알람이 전송됨.

## Main Feature

### 결제
- 회원은 강사가 게시한 강좌에 대한 결제를 요청
- Front-End에서 PG사(PortOne)의 SDK를 통해 결제를 진행
- 해당 결제 정보는 Back-End의 Purchase부에 전달되어 검증 됨
- 악의적 결제 내역으로 판단 시 PG사 API를 통해 해당 결제를 취소
- 검증 후 Member 서비스 와의 통신을 통해 해당 강좌를 게시한 강사에게 현금으로 전환 가능한 포인트를 지급 
- PG사를 통한 결제 취소를 포함한 결제 내역, 포인트 지급 내역을 모두 DB에 저장.
- 참여 가능한 인원 수 보다 강좌를 결제한 인원 수가 많을 시 
- Post 서비스 와의 통신을 통해 해당 게시글의 상태를 “마감”으로 변경

### 채팅
- 외부 메시지 브로커로 Redis를 사용해 실시간 채팅이 이루어짐.
- 채팅방을 생성한 뒤 메시지 전송 시 해당 Topic을 Subscribe하고 있는 사용자들에게 메시지 전송
- PT에 대한 채팅방은 1:N, 사용자간의 채팅방은 1:1로 구현
- 채팅 메시지와 채팅방 데이터는 빠른 조회, 쓰기 성능을 요구하고 이미지, 동영상등의 데이터가 저장가능해야 하므로 schema-less한 NoSQL(MongoDB)를 사용해 저장됨.

## Demo


