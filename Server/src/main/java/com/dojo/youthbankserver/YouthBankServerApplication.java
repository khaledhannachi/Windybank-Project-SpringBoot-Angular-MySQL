package com.dojo.youthbankserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;



@SpringBootApplication
public class YouthBankServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(YouthBankServerApplication.class, args);
	}

}
