package com.shizubro.cardcollection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CardCollectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardCollectionApplication.class, args);
	}

}
