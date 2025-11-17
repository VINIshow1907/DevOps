package br.com.lojaads.lojaads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LojaadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaadsApplication.class, args);
		System.out.println(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"));
	}

}
