## 내 답변
```
데이터베이스 커넥션 풀이란 데이터베이스 연결을 위해 미리 일정 개수의 커넥션 객체를 생성해 풀이라는 공간을 저장합니다.
필요할 때마다 풀에서 커넥션을 빌려쓰고, 다 쓰면 다시 풀에 반납해 재활용하는 방식입니다.

이것을 사용하지 않는다면 커넥션 생성과 해제에 많은 시스템 자원을 소모하고, 불필요한 자원이 낭비됩니다.
또한 동시 접속자 수가 많아지면 오버헤드가 발생하고 잦은 커넥션 생성은 서버에 부하를 줍니다.
```

<br>

## 내 정리
- **일반적인 DB 접근 동작 과정**
  1. JDBC Driver 로드
     - DB 통신을 위한 Driver 로드
  2. Connection 객체 획득
     - `getConnection()`을 통해 DB와 연결을 맺음
     - 이 과정에서 네트워크 통신과 인증 절차 등 여러 복잡하고 시간이 오래 걸리는 작업 발생
  3. SQL 쿼리 실행
     - Connection 객체를 사용해 SQL 쿼리를 실행하고 결과를 받음
  4. 리소스 반환
     - 작업이 완료되면 Connection, Statement, ResultSet 등의 리소스를 `close()`해 반환
  - **`매번 접근 과정 수행 시 발생하는 문제점`**
    1. 성능 저하
       - Connection 생성 및 해제에 많은 시간과 시스템 자원이 소모됨
       - 동시 접속자 수가 많아질수록 오버헤드가 더욱 커짐
    2. 자원 낭비
       - Connection을 생성하고 해제하는 과정에서 불필요한 자원 낭비 발생 가능
    3. DB 부하 증가
       - 잦은 Connection 생성 및 해제는 DB 서버에 영향을 줌
- **DBCP(DataBase Connection Pool)**
  - DB 연결을 효율적으로 관리하기 위한 기술
  - 웹 애플리케이션이나 여러 사용자가 동시에 DB에 접근해야 하는 환경에서 매우 중요한 역할 수행
  - **미리 일정 개수의 Connection 객체를 생성**해 **Pool**이라는 공간에 저장
  - 필요할 때마다 Pool에 Connection을 **빌려쓰고**, 다 쓴 후 Pool에 **반납**해 재활용
  - Connection 생성 및 해제 오버헤드를 줄여 애플리케이션의 응답 속도를 향상
  - 동시 접속자 수가 많아질 때 Connection 수를 제한해 DB 서버에 과부하가 걸리는 것을 방지하고 애플리케이션의 안정성을 높임
  - Connection Pool의 다양한 설정(최소/최대 Connection 수, 대기 시간 등)을 통새 시스템 상황에 맞게 Connectino을 효율적으로 관리
  - **`동작 과정`**
    1. **애플리케이션 시작 시 Connection Pool 초기화**
       - WAS가 시작될 때 미리 설정된 개수의 Connection 객체를 생성해 Connection Pool에 저장
       - 이 Connection들은 모두 DB에 연결이 완료된 상태
    2. **Connection 요청**
       - 애플리케이션에서 DB 작업이 필요해 Connection을 요청
       - DBCP는 Connection Pool에서 사용 가능한 Connection을 찾아 빌려줌
         - Pool에 사용 가능한 Connection이 있는 경우 => 즉시 제공
         - Pool에 사용 가능한 Connection이 없지만, Pool에 최대 크기에 도달하지 않은 경우 => 새로운 Connection을 생성해  Pool을 추가한 후 제공
         - Pool에 사용 가능한 Connection도 없고, Pool에 최대 크기에 도달한 경우 => Connection이 반환될 때까지 지정된 시간만큼 대기, 대기시간 내 Connection을 얻지 못한 경우 예외 발생
    3. **SQL 쿼리 실행**
        - 빌려온 Connection을 사용해 SQL 쿼리 수행
    4. **Connection 반환**
        - DB 작업이 완료되면 애플리케이션은 Connection의 `close()`를 호출
        - Connection은 실제 닫히는 것이 아닌 Connection Pool에 다시 반환되어 다른 다음 요청에 재사용
    5. **Connection 관리**
        - DBCP는 Connection Pool에 있는 Connection을 주기적으로 관리
        - Idle Connection 관리
          - 오랫동안 사용되지 않은 Connection을 제거
          - 최소한의 Connection 수를 유지하기 위해 추가 생성
        - Dead Connection 관리
          - 네트워크 단절 등으로 끊어진 Connection을 감지하고 Pool에서 제거
        - Connection 수명 관리
          - `maxLifeTime` 설정을 통해 일정 시간 사용된 Connection은 Pool에서 제거하고 새로운 Connection으로 교체
  - **`커넥션 풀 사이즈가 클수록 좋나요?`**
    - 커넥션을 사용하는 주체는 스레드이기 때문에 커넥션과 스레드를 연결지어 생각해야 함
    - **커넥션 풀 사이즈 > 스레드 풀 사이즈**
      - 스레드가 모두 사용하지 못해 리소스 낭비 발생
    - **커넥션 풀 사이즈 < 스레드 풀 사이즈**
      - 스레드가 커넥션이 반환되기를 기다려야 하기 때문에 작업 지연 발생
