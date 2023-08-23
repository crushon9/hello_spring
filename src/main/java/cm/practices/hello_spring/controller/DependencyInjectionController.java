package cm.practices.hello_spring.controller;

import cm.practices.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;


/* DI에는 3가지 방식이 있다
필드 주입, setter 주입, 생성자 주입
의존관계가 런타임 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.*/
public class DependencyInjectionController {

    @Autowired
    private MemberService memberService; // 필드주입 (권장되지 않음)

    @Autowired // 생성자주입 (요즘에 권장되는 방법)
    public DependencyInjectionController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Autowired // Setter주입 (생성자로 생성은 따로 되고 나중에 setter로 주입. 문제는 public이라서 뒤에 바뀌게되는 위험이 있다)
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

}
