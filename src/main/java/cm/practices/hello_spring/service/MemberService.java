package cm.practices.hello_spring.service;

import cm.practices.hello_spring.domain.Member;
import cm.practices.hello_spring.repository.MemberRepository;
import cm.practices.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 컨트롤 + 알트 + v = 리턴 타입을 찾아서 담아줌
// 컨트롤 + t = 메소드 추출

// service는 비즈니스에 맞게 기능을 구현하고, repository는 DB 기능에 맞춰서 구현한다 
public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member); // name이 같은 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 회원 중복 확인
    private void validateDuplicateMember(Member member) {
        /**
         * Optional<Member> result = memberRepository.findByName(member.getName());
         * result.ifPresent(m -> {
         *      throw new IllegalStateException("이미 존재하는 회원입니다.");
         * });
         */

        // result로 안담고 바로 ifPresent로 연결하는 식으로 표현
        // Member를 Optional에 담으면 null 처리 뿐 아니라 Optional의 메소드를 쓸수있다 (ifPresent 등등)
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