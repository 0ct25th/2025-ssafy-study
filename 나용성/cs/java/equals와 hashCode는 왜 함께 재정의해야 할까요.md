## 내 답변
```
equals()와 hashCode()를 함께 재정의해야 하는 이유는 이 두 메서드가 객체의 '동등성'과 '해시값'을 담당하기 때문이며,
특히 HashSet이나 HashMap과 같은 해시값을 사용하는 컬렉션 프레임워크의 동작을 예측 가능하게 만들기 위해서입니다.
두 메서드를 함께 재정의하지 않으면, `equals()`로 같은 객체로 판단되더라도 `hashCode()`가 다르거나 그 반대인 경우
예기치 않은 동작이 발생할 수 있습니다. 

```
<br>

## 내 정리
- 기본 개념
  - equals(): 두 객체가 논리적으로 같은지 판단하는 메서드
  - hashCode(): 객체의 해시 코드 값을 반환하는 메서드 (Hash 기반 컬렉션에서 사용)

- Hash 기반 컬렉션의 동작 방식
  - HashMap, HashSet 등은 객체의 동등성을 2단계로 확인
    - hashCode() 비교 → 해시 값이 같은 버킷 찾기
    - equals() 비교 → 버킷 내에서 실제 같은 객체인지 확인


##### 문제 상황별 분석
📌 equals만 재정의한 경우
```
Set<Person> people = new HashSet<>();
people.add(new Person("홍길동", 20));
people.add(new Person("홍길동", 20)); // 논리적으로 같은 객체
System.out.println(people.size()); // 예상: 1, 실제: 2
# 문제점: hashCode가 다르기 때문에 다른 버킷에 저장되어 중복 제거가 안됨
```

📌 hashCode만 재정의한 경우
```
// hashCode는 같지만 equals는 재정의 안함
Map<Person, String> map = new HashMap<>();
map.put(person1, "값");
map.get(person2); // person1과 논리적으로 같지만 null 반환
# 문제점: 같은 버킷은 찾지만 equals 비교에서 실패하여 객체를 찾을 수 없음
```

- Object 클래스의 일반 규약
  - 핵심 규약: equals()가 true를 반환하는 두 객체는 반드시 같은 hashCode 값을 가져야 함
  - equals 비교 정보가 변경되지 않으면 hashCode는 항상 같은 값 반환
  - equals()가 같다면 → hashCode()도 같아야 함
  - equals()가 다르다고 해서 hashCode가 꼭 달라야 하는 건 아님

##### 올바른 구현 방법
```
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return age == person.age && Objects.equals(name, person.name);
}

@Override
public int hashCode() {
    return Objects.hash(name, age); // 같은 필드로 해시 생성
}
```

- 핵심 포인트
  - Hash 기반 컬렉션의 올바른 동작을 위해 필수
  - 논리적으로 같은 객체는 같은 해시 코드를 가져야 함
  - IDE나 Lombok의 @EqualsAndHashCode를 활용하면 자동으로 함께 생성
  - 둘 다 재정의하거나 둘 다 하지 않아야 함
