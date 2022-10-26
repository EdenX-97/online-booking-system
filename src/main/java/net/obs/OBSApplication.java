package net.obs;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class OBSApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OBSApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/"));
		app.run(args);
	}
}
