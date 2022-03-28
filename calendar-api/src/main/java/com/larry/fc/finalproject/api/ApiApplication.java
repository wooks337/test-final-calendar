package com.larry.fc.finalproject.api;

import com.larry.fc.finalproject.core.SimpleEntity;
import com.larry.fc.finalproject.core.SimpleEntityRepository;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Entity 클래스가, Entity를 사용하고자 하는 application main package와 전혀 다른 package에 있을 때 사용합니다.
@EntityScan("com.larry.fc.finalproject.core")
/*JPA Repository 빈을 활성화하는 어노테이션입니다.
아래 코드처럼 별도로 basePackages 속성을 주지 않으면 @SpringBootApplication에 설정한 빈 scan 범위와 동일한 범위로 빈을 scan 합니다.*/
@EnableJpaRepositories("com.larry.fc.finalproject.core")
// @CreatedDate 같은  Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.EnableJpaAuditing
@RestController
@SpringBootApplication
public class ApiApplication {

	private final SimpleEntityRepository repository;

	public ApiApplication(SimpleEntityRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<SimpleEntity> findAll() {
		return repository.findAll();
	}

	@GetMapping("/save")
	public SimpleEntity saveOne() {
		return repository.save(new SimpleEntity());
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
