package nl.ordina.recruitmentexperience.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "nl.ordina.recruitmentexperience")
public class RecruitmentExperienceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentExperienceApplication.class, args);
	}

}
