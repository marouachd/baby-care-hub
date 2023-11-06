package co.simplon.babycarehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BabyCareHubApplication {

    public static void main(String[] args) {
	SpringApplication.run(BabyCareHubApplication.class,
		args);
    }

}
