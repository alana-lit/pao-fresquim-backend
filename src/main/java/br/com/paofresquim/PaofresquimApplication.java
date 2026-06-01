package br.com.paofresquim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PaofresquimApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaofresquimApplication.class, args);
	}

}
