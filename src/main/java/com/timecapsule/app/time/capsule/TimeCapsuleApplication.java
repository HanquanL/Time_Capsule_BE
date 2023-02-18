package com.timecapsule.app.time.capsule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TimeCapsuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeCapsuleApplication.class, args);
	}

}
