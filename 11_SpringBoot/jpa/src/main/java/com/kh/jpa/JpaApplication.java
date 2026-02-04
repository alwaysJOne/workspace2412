package com.kh.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA 애플리케이션 메인 클래스
 * @EnableJpaAuditing : JPA Auditing 기능 활성화
 * - BaseTimeEntity의 @CreatedDate, @LastModifiedDate가 자동으로 작동
 * - Notice의 @CreatedDate가 자동으로 작동
 */
@EnableJpaAuditing
@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

}
