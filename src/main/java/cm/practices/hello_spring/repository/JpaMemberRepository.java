package cm.practices.hello_spring.repository;

import cm.practices.hello_spring.domain.Member;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    
    private final EntityManager em; // jpa를 동작하는 주체
    
    public JpaMemberRepository(EntityManager em) { // 생성자 주입
        this.em = em;
    }
    public Member save(Member member) {
        em.persist(member); // persist : 영구저장
        return member;
    }
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // find : 조회
        return Optional.ofNullable(member);
    }
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // createQuery : JPQL쿼리 (테이블이 아닌 객체를 대상으로 쿼리작성)
                .getResultList();
    }
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}
