package cm.practices.hello_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}

/** [인텔리제이 단축키]
 * 컨트롤 + 알트 + v = 리턴 타입을 찾아서 담아줌
 * 컨트롤 + t = 메소드 추출
 * 클래스이름포커스 + 알트 + 엔터 = 테스트 코드 자동생성
 * getter setter 단축키 : 우클릭 generate */

/**
 * 컨트롤러: 웹 MVC의 컨트롤러 역할
 * 서비스: 핵심 비즈니스 로직 구현
 * 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
 * 도메인: 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨
 */

// /c/Program Files (x86)/H2/bin 경로에서 /h2.bat 실행 후 서버 올리기
// DB는 build.gradle에 의존되어있어 스프링 시작시 자동 시작되며 application.properties 에 DB연결정보가 있음
