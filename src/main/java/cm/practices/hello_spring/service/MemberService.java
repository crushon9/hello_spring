package cm.practices.hello_spring.service;

import cm.practices.hello_spring.domain.Member;
import cm.practices.hello_spring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
// service는 비즈니스에 맞게 기능을 구현하고, repository는 DB 기능에 맞춰서 구현한다
public class MemberService {

    private final MemberRepository memberRepository;

    // DI : 기본생성자는 없고, 매개변수 있는 생성자에 레파지토리 인터페이스를 주입 (기본생성자는 config에 작성)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member); // name이 같은 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 회원 중복 확인
    private void validateDuplicateMember(Member member) {
        /** 변경전 코드
         * Optional<Member> result = memberRepository.findByName(member.getName());
         * result.ifPresent(m -> {
         *      throw new IllegalStateException("이미 존재하는 회원입니다.");
         * });
         */

        /** 리팩토링 후 코드
         * result로 안담고 바로 ifPresent로 연결하는 식으로 표현
         * Member를 Optional에 담으면 null 처리 뿐 아니라 Optional의 메소드를 쓸수있다 (ifPresent 등등)
         * ifPresent(m -> : m은 앞의 findByName이 반환한 Optional<Member>객체, 그 객체가 존재하면 throw
         */
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });


    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}