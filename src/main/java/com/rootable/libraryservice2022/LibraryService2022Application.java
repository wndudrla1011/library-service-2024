package com.rootable.libraryservice2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Jpa Auditing 활성화
@SpringBootApplication
public class LibraryService2022Application {

	public static void main(String[] args) {
		SpringApplication.run(LibraryService2022Application.class, args);
	}

}
