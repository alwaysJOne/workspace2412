package com.kh.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication
스프링부트 애플리케이션을 실행하는 메인클래스를 지정하는 어노테이션으로 스프링부트실행을 위한 자동설정을 진행한다.
1. @ComponentScan을 사용한 것 같이 동등한 위치이거나 하위에 있는 모든 패키지를 탐색하며 빈(Bean)을 등록한다.
2. Tomcat(내장 웹서버)의 자동실행
 */

@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
