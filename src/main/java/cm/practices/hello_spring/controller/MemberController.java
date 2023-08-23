package cm.practices.hello_spring.controller;

import cm.practices.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/* 컴포넌트 스캔과 자동 의존관계 설정
 * @Component
 * 개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 어노테이션
 * @Controller, @Service, @Repository의 클래스를 찾아보면 위에 @Component 어노테이션이 붙어있음
 * 스프링 처음 시작시 컴포넌트를 스캔해서 해당하는 클래스는 객체를 생성하여 스프링 컨테이너에 담아두고
 * Autowired가 있으면 해당되는 컴포넌트 객체를 연결해줌
 * 참고) 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다
 * 따라서 같은 스프링 빈이면 모두 같은 인스턴스다. 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.
 * 참고) 스캔의 범위는 main 메서드가 있는 패키지 하위이다 */
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    // (@Controller, @Service, @Repository 등의 어노테이션이 붙어있는 클래스를 스프링이 컴포넌트 스캔하여 스프링 컨테이너에 객체로 넣어둠)
    // 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 한다.
    // new 로 생성해서 직접 넣는 방식 -> @Autowired 스프링이 대신 넣어주는 방식으로 진화
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
