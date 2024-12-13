package com.fredericomf.screenmatch_web;

import com.fredericomf.screenmatch_web.principal.Principal;
import com.fredericomf.screenmatch_web.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchWebApplication.class, args);
	}

}
