package cm.practices.hello_spring.repository;

import cm.practices.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

// 다형성을 이용하기 위해 인터페이스 생성 (DB가 변경될수도 있다는 상황을 가정)
public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();

}