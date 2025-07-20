package com.ms_spring_brgy.PDF_Builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PdfBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfBuilderApplication.class, args);
	}

}
