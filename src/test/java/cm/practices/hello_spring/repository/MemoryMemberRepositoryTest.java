package cm.practices.hello_spring.repository;

import cm.practices.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/* 원래 class 이름 뒤에 test를 붙이는 것이 관행
 * Test 코드를 먼저 만들고 구현하는 것을 TDD라 함 (테스트 주도 개발) */

import static org.assertj.core.api.Assertions.*; // static import하면 assertThat메소드를 바로 사용가능

class MemoryMemberRepositoryTest { // class를 실행하면 test 메소드 모두 동작된다. 순서는 보장되지 않는다.
    /*
     * 한번에 여러 테스트를 실행하면 메모리에 직전 테스트의 결과가 남을 수 있다.
     * @AfterEach 를 사용하면 각 테스트가 종료될 때 마다 메모리에 저장된 데이터를 삭제한다.
     */
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test // @Test 해당 메소드를 main 메소드처럼 그냥 실행할수있다
    public void save() {
        //given
        Member member = new Member();
        member.setName("cheongmi");
        //when
        repository.save(member);
        //then
        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member, result); // 두 객체가 일치하는지 비교
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

}
