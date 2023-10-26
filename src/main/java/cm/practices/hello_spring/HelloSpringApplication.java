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

// /c/Program Files (x86)/H2/bin 경로에서 ./h2.bat 실행 후 서버 올리기
// DB는 build.gradle에 의존되어있어 스프링 시작시 자동 시작되며 application.properties 에 DB연결정보가 있음

/** 강의 목차
 * 프로젝트 환경설정
 * 스프링 웹 개발 기초
	정적 컨텐츠
	MVC와 템플릿 엔진
    API
 * 회원 관리 예제 - 백엔드 개발
	비즈니스 요구사항 정리
	회원 도메인과 리포지토리 만들기
	회원 리포지토리 테스트 케이스 작성
	회원 서비스 개발
	회원 서비스 테스트
 * 스프링 빈과 의존관계
	컴포넌트 스캔과 자동 의존관계 설정
	자바 코드로 직접 스프링 빈 등록하기
 * 스프링 DB 접근 기술
	H2 데이터베이스 설치
	순수 Jdbc
	스프링 통합 테스트
	스프링 JdbcTemplate
	JPA
	스프링 데이터 JPA
 * AOP
	AOP가 필요한 상황
	AOP 적용 */
