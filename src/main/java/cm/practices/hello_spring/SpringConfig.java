package cm.practices.hello_spring;

import cm.practices.hello_spring.repository.JdbcTemplateMemberRepository;
import cm.practices.hello_spring.repository.MemberRepository;
import cm.practices.hello_spring.repository.MemoryMemberRepository;
import cm.practices.hello_spring.repository.JdbcMemberRepository;
import cm.practices.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/** 자바 코드로 직접 스프링 빈 등록하기
 * 참고) XML로 설정하는 방식도 있지만 최근에는 잘 사용하지 않는다.
 * 실무에서는 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 주로 컴포넌트 스캔을 사용한다.
 * 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 할 때는 설정을 통해 스프링 빈으로 등록한다.
 * 컴포넌트 스캔 방식과 Bean 설정 방식은 중복되면 오류발생 하므로 컴포넌트 스캔 어노테이션을 제거해줘야 한다*/

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // 서비스 기본생성자를 생성할때, 매개변수 있는 생성자(서비스 클래스에 있는)를 돌려준다 이때 매개변수로는 아래의 레파지토리를 돌려주는 메소드를 넣는다
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    // 개방-폐쇄 원칙(OCP, Open-Closed Principle)
    @Bean // 레파지토리를 인터페이스로 생성하였기 때문에 설정에서 구현 클래스를 변경하면, 서비스를 비롯한 다른 코드는 변경하지 않아도 됨
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}

/** 객체 지향 설계의 5원칙 SOLID
 * SRP (Single Responsibility Principle) 단일 책임 원칙
 * 한 클래스는 하나의 책임만 가져야 한다.
 * 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것이다.
 * 예를 들어 UI를 변경하는데 SQL 코드부터 서버 전반에 걸쳐 고쳐야 한다면 단일 책임 원칙을 따르지 않은 것이다.
 *
 * OCP (Open/Closed Principle) 개방-폐쇄 원칙
 * 5가지 원칙 중 가장 중요한 원칙이다.
 * 소프트웨어 요소는 확장에는 열려있으나 변경에는 닫혀있어야 한다.
 * 다형성을 활용하면 개방-폐쇄의 원칙을 잘 지킬 수가 있다.
 * 인터페이스를 구현(implement)한 새로운 클래스를 하나 만들어서 새로운 기능을 구현하더라도, 클라이언트에서는 기존 코드를 변경하지 않아도된다
 *
 * LSP (Liskov Substitution Principle) 리스코프 치환 원칙
 * 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서, 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
 * 다시 말해, 다형성에서 하위 클래스는 인터페이스 규약을 모두 지켜야 한다는 원칙이다.
 *
 * ISP (Interface Segregation Principle) 인터페이스 분리 원칙
 * 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다는 원칙이다.
 * 쉽게 말해 기능이 너무 많으면 복잡하니까 적당한 크기로 쪼개는 게 중요하다는 것이다.
 * 이 원칙을 적용하면 인터페이스가 명확해지고, 대체 가능성이 높아진다.
 *
 * DIP (Dependency Inversion Principle) 의존관계 역전 원칙
 * OCP 원칙과 함께 DIP도 정말 중요한 원칙이다.
 * 쉽게 말해, 클라이언트 코드가 구현 클래스에 의존하지 말고, 인터페이스에 의존하라는 뜻이다.
 * (구현체에 의존하지 말고 역할에 의존해야 한다.)
 * 의존성 주입(Dependency Injection)은 "프로그래머는 추상화에 의존해야지, 구체화에 의존하면 안된다."라는 원칙을 따르는 방법 중 하나이다.
 *
 * */
