## 내 답변
```
Spring MVC 패턴을 수행하기 위해 Model-View-Controller 역할을 수행하는 Spring Bean으로 등록할 때 사용하는 어노테이션입니다.

@Component는 일반적인 어노테이션으로 특별히 역할이 주어지지 않은 클래스에 사용합니다.
@Controller는 MVC에서 Controller 역할을 하며 사용자 요청과 응답을 담당하는 클래스에 사용합니다.
@Serivce는 MVC 에서 Model 역할을 하며 비즈니스 로직을 담당하는 클래스에 사용합니다.
@Repository는 MVC에서 Model 역할을 하며 DB 접근을 담당하는 클래스에 사용합니다.
```
<br>

## 내 정리
- **MVC(Model-View-Controller) 패턴**
    - SW 개발에서 사용되는 디자인 패턴
    - 애플리케이션을 3가지 역할로 나눠 구조화
        - **Controller**: 사용자의 요청을 받고 요청에 따라 모델을 호출
        - **Model**: 애플리케이션의 데이터와 비즈니스 로직을 담당
        - **View**: 모델이 가진 데이터를 사용자에게 보여주는 화면을 담당
    - 역할을 나눠 각 계층의 역할을 명확히 하고 코드의 **응집도**를 높여 **유지보수**를 쉽게 만듦
    - **MVC 1**
        - JSP와 JavaBeans를 중심으로 한 단순한 패턴
        - JSP가 Controller와 View 역할을 모두 수행
        - JSP가 요청을 받고 JavaBeans를 통해 로직을 처리
    - **MVC 2**
        - 모든 요청을 단일 서블릿(Controller)이 먼저 받아 처리
        - JSP는 오직 View의 역할만을 수행
        - 현재 대부분의 웹 프레임워크(예: Spring MVC 등)가 채택하는 방식
- `@Component`
    - Spring이 관리하는 Bean임을 나타나는 가장 일반적인 어노테이션
    - Spring의 Component Scan 기능으로 빈이 자동 등록
    - 특별히 역할이 정해지지 않은 클래스에 사용
- `@Controller`
    - MVC 패턴의 Controller 역할을 담당
    - 사용자 요청과 응답을 담당하는 클래스에 사용
        - 사용자의 HTTP 요청을 받아 비즈니스 로직을 호출하고, 응답을 반환
    - `@RequestMapping`과 같은 어노테이션을 사용해 특정 URL에 대한 요청을 처리
        - 주로 웹 요청을 처리
- `@Service`
    - MVC 패턴의 Model의 비즈니스 로직 역할을 담당
    - 비즈니스 로직을 담당하는 클래스에 사용
    - `@Controller`와 `@Repository`를 연결하는 중간 다리 역할
        - 트랜잭션 처리와 같은 핵심 비즈니스 로직을 수행
- `@Repository`
    - MVC 패턴의 Model의 데이터 접근 부분 역할을 담당
    - DB 접근을 담당하는 클래스에 사용
    - DB 예외를 스프링의 DataAccessException으로 변환하는 기능을 자동을 제공
        - DB에 종속되지 않는 예외 처리가 가능
