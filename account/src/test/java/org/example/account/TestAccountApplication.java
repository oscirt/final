package org.example.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestAccountApplication {

	public static void main(String[] args) {
		SpringApplication.from(AccountApplication::main).with(TestAccountApplication.class).run(args);
	}

}
