package cm.practices.hello_spring.repository;

import cm.practices.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository; // 기본적인 메서드 save, findAll 등이 있음

import java.util.Optional;

/** 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술
 * interface를 생성하고 JpaRepository를 extends하면
 * 스프링데이터jpa 가 자동 구현하여 스프링 bean으로 등록
 * 기본적인 메서드 save, findAll, 페이징기능 등은 만들지 않아도 있음 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
    // 공통 메서드에 없는것은 메서드 이름 규칙에 의해 jpql생성
    // select m from Member m where m.name = ?

    /** 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용하면 된다.
     * Querydsl을 사용하면 쿼리도 자바 코드로 안전하게 작성할 수 있고, 동적쿼리도 편리하게 작성할 수 있다.
     * 이 조합으로 해결하기 어려운 쿼리는 JPA가 제공하는 네이티브 쿼리를 사용하거나, 앞서 학습한 스프링 JdbcTemplate를 사용하면 된다
     */
    
}
