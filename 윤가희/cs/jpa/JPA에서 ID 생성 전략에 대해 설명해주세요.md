## 내 답변
```
ID 생성 전략이란 엔티티 식별자를 어떻게 생성하고 관리할 것인지 정의하는 것 입니다.
JPA에서는 @GeneratedValue 어노테이션을 사용해 설정하며 4가지의 전략이 존재합니다.

첫 번째 IDENTITY 전략입니다. DB에 ID 생성을 위임해 DB가 생성한 ID 값을 즉시 엔티티에 할당합니다.
두 번째 SEQUENCE 전략입니다. DB에 시퀀스 객체를 사용해 ID를 생성합니다.
세 번째 TABLE 전략입니다. ID 값을 관리하는 별도의 테이블을 만들어 ID를 생성합니다.
네 번째 AUTO 전략입니다. JPA의 구현체 Hibernate가 사용하는 DB에 맞춰 앞 3가지의 전략 중 하나를 자동으로 선택합니다.
```
<br>

## 내 정리
- 엔티티 식별자(Primary Key)를 어떻게 생성하고 관리할 것인지 정의하는 방식
    - 직접 할당: `@Id` 어노테이션만을 사용해 Id값을 직접 할당하는 방식
    - 자동 할당: `@Id`와 `@GeneratedValue`를 함께 사용해 원하는 키 생성
- `@GeneratedValue` 타입
    - GenerationType.**IDENTITY** 전략
        - DB에 ID 생성을 위임
        - MySQL의 AUTO_INCREMENT나 PostgreSQL의 SERIAL 처럼 DB가 자동으로 ID를 생성
        - **동작 방식**
            - 엔티티를 `persist()` 하는 시점에 DB에서 `INSERT` 쿼리가 실행되고 데이터 베이스가 생성한 ID 값을 즉시 엔티티에 할당
        - **장점**
            - 별도의 테이블이나 시퀀스 관리가 필요 없어 간편
        - **단점**
            - `persist()`시점에 바로 INSERT 쿼리가 나가기 때문에 쓰기 지연을 활용할 수 없음
    - GenerationType.**SEQUENCE** 전략
        - DB의 시퀀스 객체를 사용해 ID를 생성
        - Oracle, PostgreSQL 등에서 주로 사용되는 방식
        - **동작 방식**
            - persist()를 호출하면 JPA 가 먼저 DB의 시퀀스에서 다음 ID 값을 가져옴
            - 가져온 값을 엔티티에 할당
            - 트랜잭션 커밋 시점에 INSERT 쿼리를 실행
        - **장점**
            - 쓰기 지연을 활용 가능해 성능 상 이점
        - **단점**
            - 시퀀스 객체를 DB에 미리 정의 필요
    - GenerationType.**TABLE** 전략
        - ID 값을 관리하는 별도의 테이블을 만들어 ID를 생성
        - 모든 DB에서 사용할 수 있는 범용적인 전략
        - **동작 방식**
            - ID를 가져올 때마다 해당 관리 테이블에 락(Lock)을 걸고 값을 갱신 → 성능 상 이슈 발생 가능
        - **장점**
            - 모든 DB에서 동일하게 사용 가능
        - **단점**
            - 성능이 가장 낮아 실무에서 잘 사용하지 않음
    - GenerationType.**AUTO** 전략
        - 사용하는 DB의 방언에 따라 IDENTITY, SEQUENCE, TABLE 중 하나를 자동으로 선택
        - **동작 방식**
            - JPA 구현체(Hibernate)가 DB 종류를 파악해 가장 적합한 전략을 자동으로 결정
        - **장점**
            - DB를 변경해도 코드 수정이 필요없어 유연
