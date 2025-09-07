## 내 답변
```
equlas 메소드는 두 객체가 논리적으로 동일한지 비교하는 메소드 입니다.
hashCode 메소드는 객체의 해시 코드를 반환하는 메소드 입니다.

두 메소드를 함께 재정의 하지 않는다면 두 객체를 다른 객체로 인식합니다.
그래서 해시 기반 자료구조에서 중복 저장되는 문제가 발생할 가능성이 존재하기 때문에 함께 재정의 해야합니다.
```
<br>

## 내 정리
- `equals`와 `hashCode`는 **객체의 동등성**을 정의하는 메서드
- 두 메서드를 함깨 재정의 하지 않으면 **두 객체를 다른 객체로 인식**해 **중복 저장**되는 문제 발생 가능
    - **해시 기반 자료구조의 일관성을 유지**
- **equals()**
    - 두 객체가 **논리적**(ex: id가 같은 두 객체)으로 동등한지 비교하는 메서드
- **hashCode()**
    - 객체의 해시 코드를 반환하는 메서드
    - **물리적**(메모리 주소)를 비교
    - 반환 받은 값은 해시 기반 자료구조(`HashMap`, `HashSet`)에서 객체를 저장하거나 탐색할 때 사용
    - 재정의하지 않는다면 사용하는 Object의 `hashCode()`는 객체의 고유한 주소를 사용하기 때문에 객체마다 다른 값을 반환
- **규약**
    - `equals()`가 true를 반환하면, 두 객체의 `hashCode()` 값은 반드시 같아야 함
    - `hashCode()` 값이 같다고 해서, 두 객체가 반드시 `equals()`로 true를 반환하는 것은 아님
    - `equals()`가 false를 반환하면, `hashCode()` 값은 달라도 됨
- **코드 예시**
    1. `hashCode()`는 재정의하지 않은 경우
        
        ```java
        class Member {
            private Long id;
            private String name;
        
            public Member(Long id, String name) {
                this.id = id;
                this.name = name;
            }
        
            // hashCode()는 재정의하지 않음.
            // 기본 hashCode()는 객체의 메모리 주소 기반 값을 반환
        
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Member member = (Member) o;
                return this.id != null && this.id.equals(member.id);
            }
        }
        
        public class Main {
            public static void main(String[] args) {
                Set<Member> memberSet = new HashSet<>();
        
                Member member1 = new Member(1L, "김철수");
                Member member2 = new Member(1L, "김철수");
        
                // 논리적으로는 두 객체가 같음
                System.out.println("member1.equals(member2) : " + member1.equals(member2)); // true
        
                // 1. HashSet에 member1 추가
                memberSet.add(member1);
        
                // 2. HashSet에 member2 추가
                // HashSet은 hashCode()를 먼저 비교하므로, 두 객체를 다른 객체로 인식
                memberSet.add(member2);
        
                // 결과적으로, 논리적으로 같은 객체가 중복해서 저장됨
                System.out.println("Set에 저장된 객체 수 : " + memberSet.size()); // 2
            }
        }
        ```
        
        - 실행 결과
            
            ```bash
            member1.equals(member2) : true
            Set에 저장된 객체 수 : 1
            ```
            
    2. `equals()`와 `hashCode()` 둘 다 재정의한 경우
        
        ```java
        class Member {
            private Long id;
            private String name;
        
            public Member(Long id, String name) {
                this.id = id;
                this.name = name;
            }
        
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Member member = (Member) o;
                return this.id != null && this.id.equals(member.id);
            }
        
            @Override
            public int hashCode() {
                return Objects.hash(id); // id를 기준으로 해시 코드 생성
            }
        }
        
        public class Main {
            public static void main(String[] args) {
                Set<Member> memberSet = new HashSet<>();
        
                Member member1 = new Member(1L, "김철수");
                Member member2 = new Member(1L, "김철수");
        
                // 1. 논리적으로 두 객체가 같음
                System.out.println("member1.equals(member2) : " + member1.equals(member2));
        
                // 2. 두 객체의 해시 코드가 같음
                System.out.println("member1.hashCode() : " + member1.hashCode());
                System.out.println("member2.hashCode() : " + member2.hashCode());
                System.out.println("두 해시 코드 같음 여부: " + (member1.hashCode() == member2.hashCode()));
        
                // 3. HashSet에 member1과 member2 추가
                memberSet.add(member1);
                memberSet.add(member2);
        
                // 결과적으로, 논리적으로 같은 객체는 중복 저장되지 않음
                System.out.println("Set에 저장된 객체 수 : " + memberSet.size());
            }
        }
        ```
        
        - 실행 결과
            
            ```bash
            member1.equals(member2) : true
            member1.hashCode() : 32
            member2.hashCode() : 32
            두 해시 코드 같음 여부: true
            Set에 저장된 객체 수 : 1
            ```
