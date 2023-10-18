package cm.practices.hello_spring.controller;

import cm.practices.hello_spring.service.MemberService;


/* DI에는 3가지 방식이 있다
필드 주입, setter 주입, 생성자 주입
의존관계가 런타임 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다
객체 의존관계를 외부(클래스 내부가 아닌 스프링컨테이너가 @Autowired로 넣거나, 외부 설정으로 넣거나)에서 넣어주는 것을 DI (Dependency Injection) 의존성 주입이라 한다 */

/* 스프링 빈 등록에는 2가지 방식이 있다
컴포넌트 스캔, 스프링 빈 수동 설정
컴포넌트 스캔 방식(@Autowired)과 빈 수동 설정(@Bean) 방식은 중복되면 오류가 발생한다
@Autowired 를 통한 DI는 컴포넌트 스캔에 의해 스프링이 관리하는 객체에서만 동작한다. 개발자가 직접 생성한 객체에서는 동작하지 않는다.*/

public class DependencyInjectionController {

    // 필드주입 (권장되지 않음)
    private MemberService memberService;

    // 생성자주입 (요즘에 권장되는 방법) 생성자가 1개만 있으면 @Autowired 는 생략할 수 있다.
    public DependencyInjectionController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Setter주입 (생성자로 생성은 따로 되고 나중에 setter로 주입. 문제는 public이라서 뒤에 바뀌게되는 위험이 있다)
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

}