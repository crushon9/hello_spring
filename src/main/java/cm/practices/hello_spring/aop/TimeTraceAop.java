package cm.practices.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/** AOP: Aspect Oriented Programming
 * 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern) 분리
 * aop를 사용하면 프록시 class를 스프링빈에 담고 aop코드를 실행하다가 joinPoint.proceed()에서 실제 클래스를 호출하여 연결함
 * getClass로 log 출력시 memberService = class cm.practices.hello_spring.service.MemberService$$EnhancerBySpringCGLIB$$441c2635 확인 가능 */
@Aspect // aop annotation
@Component // bean 등록은 @Component보다 config에 설정하는것이 가시적
public class TimeTraceAop {
    @Around("execution(* cm.practices.hello_spring..*(..))") // 해당 패키지의 모든 메소드에 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); // 다른 메소드가 실행되는 위치
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +"ms");
        }
    }
}
