package com.massal.twittertime.twittertime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;



@SpringBootApplication
public class TwittertimeApplication {
	public static void main(String[] args) {
		SpringApplication.run(TwittertimeApplication.class, args);
	}

}
