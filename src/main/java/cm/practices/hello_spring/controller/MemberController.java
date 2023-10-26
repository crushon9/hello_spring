package cm.practices.hello_spring.controller;

import cm.practices.hello_spring.domain.Member;
import cm.practices.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/** 컴포넌트 스캔과 자동 의존관계 설정
 * @Component
 * 개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 어노테이션
 * @Controller, @Service, @Repository의 클래스를 찾아보면 위에 @Component 어노테이션이 붙어있음
 * 스프링 처음 시작시 컴포넌트를 스캔해서 해당하는 클래스는 객체를 생성하여 스프링 컨테이너에 담아두고
 * @Autowired가 있으면 해당되는 컴포넌트 객체를 연결해줌
 * 참고) 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다
 * 따라서 같은 스프링 빈이면 모두 같은 인스턴스다. 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.
 * 참고) 스캔의 범위는 main 메서드가 속한 패키지 하위이다 */
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
    // (@Controller, @Service, @Repository 등의 어노테이션이 붙어있는 클래스를 스프링이 컴포넌트 스캔하여 스프링 컨테이너에 객체로 넣어둠)
    // 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 한다.
    // new 로 생성해서 직접 넣는 방식은 코드가 바뀌면 수정할 부분이 많기 때문에 DI로 의존관계를 느슨하게 한다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = "+memberService.getClass()); // AOP Proxy class 확인용 log
        // memberService = class cm.practices.hello_spring.service.MemberService$$EnhancerBySpringCGLIB$$441c2635
    }

    /** 회원가입 */
    @GetMapping("/members/new") // get방식(url로 직접호출) html페이지 반환
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // form태그에서 post방식으로 호출
    public String create(MemberForm form) { // 파라미터 MemberForm form안의 setter메소드이름과 input태그의 name이 매칭
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/"; /* 회원가입 후 루트(/) 홈화면으로 리다이렉트 */
    }

    /** 회원조회 */
    @GetMapping("/members")
    public String list(Model model) { // model은 size 0인 상태로 파라미터로 들어옴
        List<Member> members = memberService.findMembers();
        model.addAttribute("membersss", members);
        return "members/memberList"; // return에 model을 보내지않아도 값이 전달 되네?
        /** 데이터를 view로 전달하는 방식
         * 1. Model 객체
         * Controller의 메서드는 Model타입 객체를 parameter로 받을 수 있다.
         * 개발자는 직접 Model 객체를 생성하지 않고 parameter로 선언만 해주면 스프링의 front controller가 argument로 넣어준다
         * JSP Servlet의 request나 session 내장 객체와 비슷한 역할이다.
         * 컨트롤러 메소드 내부에서 데이터를 꺼내서 model에 담으면 return없이 스프링이 view에 전달한다.
         *
         * 2. @ModelAttribute 어노테이션
         * parameter 앞에 @ModelAttribute 를 추가하면 view에 전달된다.
         * @ModelAttribute("key") : 하나의 데이터를 받을수도 있고 ?key=111
         * @ModelAttribute Member member : setter가 존재하는 클래스의 멤버변수들을 그룹으로 받을수도 있다 ?name=JJY&age=10
         *
         * @RequestParam과 @ModelAttribute 둘다 파라미터값을 받아 view로 넘겨주는 기능은 같다. 차이점은 매핑 갯수이다
         * @RequestParam : 1:1매핑만 가능
         * @ModelAttribute : 1:1매핑, 객체매핑 둘다 가능
         */
    }

}
