package br.edu.ifpb.upcensus;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableBatchProcessing
public class UpcensusApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpcensusApplication.class, args);
	}

}
