package com.gianca1994.umcredits;

import com.gianca1994.umcredits.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
public class UmcreditsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmcreditsApplication.class, args);
	}
}
