package com.bit.pms;

import com.bit.pms.config.JPAConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ JPAConfig.class})
@SpringBootApplication
public class LMSApplication {
	public static void main(String[] args) {
		SpringApplication.run(LMSApplication.class, args);
	}
}
