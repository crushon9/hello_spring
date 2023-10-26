package cm.practices.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

// AOP: Aspect Oriented Programming
// 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern) 분리
@Aspect // aop annotation
// bean 등록은 java config에 설정되어있음
public class TimeTraceAop {
    @Around("execution(* cm.practices.hello_spring..*(..))") // 해당 패키지의 모든 메소드에 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +"ms");
        }
    }
}
