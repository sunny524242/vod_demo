package com.example.vod_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class VodDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VodDemoApplication.class, args);
	}
}
