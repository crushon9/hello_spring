package cm.practices.hello_spring.repository;

import cm.practices.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    /*
     * 실무에서는 동시성 문제로 ConcurrentHashMap, AtomicLong 등을 사용
     * */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 시스템 아이디를 시퀀스 증가시켜서 set하고
        store.put(member.getId(), member); // 방금 만든 아이디를 가져와서 저장
        return member;
    }

    /* Optional
     * 자바 8 이전 버전에서 객체가 null값을 반환할 경우 예외처리 과정을 통해 NullPointException을 방지했었다.
     * 자바 8 버전부터는 java.util.Optional<T> 이라는 새로운 래퍼 클래스(Wrapper class)가 추가되었다.
     * 옵셔널을 사용하면 이전 버전의 복잡한 과정을 간편하게 처리할 수 있기 때문에 유용하게 사용할 수 있다. */
    @Override
    public Optional<Member> findById(Long id) {
        // store가 key, value 니깐 id(key)를 넣어서 member(value)를 가져옴
        return Optional.ofNullable(store.get(id));
    }

    /* Stream
     * 'Java 8'부터 지원되기 시작한 기능이다.
     * 컬렉션에 저장되어 있는 엘리먼트들을 하나씩 순회하면서 처리할 수 있는 코드패턴이다.
     * 람다식과 함께 사용되어 컬렉션에 들어있는 데이터에 대한 처리를 매우 간결한 표현으로 작성할 수 있다.
     * 또한, 내부 반복자를 사용하기 때문에 병렬처리가 쉽다는 점이 있다.
     * filter() findAny() findFirst() 등과 함께 쓰임 */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
