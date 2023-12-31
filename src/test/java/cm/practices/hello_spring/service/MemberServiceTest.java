package cm.practices.hello_spring.service;

import cm.practices.hello_spring.domain.Member;

// Assertions 관련 static import
// static import 단축키 : alt + enter
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import cm.practices.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // BeforeEach : 각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고 의존관계도 새로 맺어준다.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("wonjung");

        //when
        Long saveId = memberService.join(member);

        //then
        Member one = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(one.getName());
    }

    @Test
    public void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("wonjung");

        Member member2 = new Member();
        member2.setName("wonjung");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /** 리팩토링 이전 코드
        try {
            memberService.join(member2);
            fail("예외가 발생하지 않았음 실패");
        } catch (IllegalStateException e) { // validateDuplicateMember에서 throw한 예외가 잡힘
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
         */
    }

}