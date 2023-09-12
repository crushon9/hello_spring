package cm.practices.hello_spring;

import cm.practices.hello_spring.repository.MemberRepository;
import cm.practices.hello_spring.repository.MemoryMemberRepository;
import cm.practices.hello_spring.repository.JdbcMemberRepository;
import cm.practices.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/* 자바 코드로 직접 스프링 빈 등록하기
 * > 참고) XML로 설정하는 방식도 있지만 최근에는 잘 사용하지 않는다.
 * 실무에서는 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 주로 컴포넌트 스캔을 사용한다.
 * 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 할 때는 설정을 통해 스프링 빈으로 등록한다.
 * 즉, 예제에서 메모리 리포지토리를 다른 리포지토리로 변경할 예정이므로, 컴포넌트 스캔 방식 대신에 자바 코드로 스프링 빈을 설정 
 * 컴포넌트 스캔 방식과 Bean 설정 방식은 중복되면 오류발생 */

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // 서비스를 호출하면, 레파지토리를 주입한 서비스 객체를 돌려준다
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // 레파지토리 객체를 여기서 바꿔끼운다
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
