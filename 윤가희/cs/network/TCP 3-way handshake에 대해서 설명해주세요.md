## 내 답변
```
TCP는 신뢰할 수 있는 통신을 위해 연결 상태를 관리하는 프로토콜입니다.
연결을 수립하기 위해 클라이언트와 서버는 3-Way Handshake를 수행합니다.

3-Way Handshake는 총 3단계로 이뤄집니다.

1. 클라이언트가 서버에게 접속을 요청하며 SYN 세그먼트를 보냅니다.
2. 서버는 클라이언트에 접속 요청을 수락하는 응답으로 SYN + ACK 세그먼트를 보냅니다.
3. 클라이언트는 서버의 응답을 받고 ACK 세그먼트를 보내 연결을 최종적으로 확정합니다.
```

<br>

## 내 정리
- **TCP(Transmission Control Progocol)**
    - 신뢰할 수 있는 통신을 위한 연결형 프로토콜(Stateful Protocol)
    - 통신하기 전 연결을 수립하고 통신이 끝나면 연결을 종료
    1. **연결 수립: 3-way Handshake**
        <img width="825" height="375" alt="image" src="https://github.com/user-attachments/assets/aaa1a9a3-ec02-4563-8a70-14ca18e19da6" />
        <img width="1025" height="347" alt="image" src="https://github.com/user-attachments/assets/751f8d86-9e57-4f03-abef-ef2a5fc7255f" />

    2. **데이터 송수신**
        1. TCP의 신뢰성을 위해 오류제어, 흐름제어, 혼잡제어 수행
    3. **연결 종료: 4-way Handshake**
       <img width="825" height="375" alt="image" src="https://github.com/user-attachments/assets/f044ea0a-8ad3-41d2-882d-5ac449a68e0c" />
       <img width="1025" height="347" alt="image" src="https://github.com/user-attachments/assets/1ea210dd-b1eb-4239-adac-9c903e67b05c" />
[이미지 출처: https://www.youtube.com/watch?si=gMNLvRgsC-2Ve420&v=d6pUy1Z56h8&feature=youtu.be]
