package cm.practices.hello_spring.service;

import cm.practices.hello_spring.domain.Member;
import cm.practices.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 순수한 자바코드로된 단위 테스트가 스프링 컨테이너가 작동하는 통합 테스트보다 좋은 테스트 이다
@SpringBootTest // 스프링 컨테이너가 실제로 작동해서 통합 테스트 가능한 어노테이션
@Transactional // 테스트 케이스에 이 어노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 롤백한다. DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
class MemberServiceIntegrationTest {

    @Autowired // 테스트에서는 필드주입해도 무방
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("testsdjpa"); // @Transactional 로 해당 데이터는 DB에 남지 않을 것임

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
        member1.setName("testTran");

        Member member2 = new Member();
        member2.setName("testTran");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}