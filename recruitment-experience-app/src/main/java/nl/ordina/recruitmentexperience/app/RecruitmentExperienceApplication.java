package nl.ordina.recruitmentexperience.app;

import nl.ordina.recruitmentexperience.core.DocumentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@SpringBootApplication
@EntityScan(basePackages = "nl.ordina.recruitmentexperience.data")
@ComponentScan(basePackages = "nl.ordina.recruitmentexperience")
@EnableJpaRepositories(basePackages = "nl.ordina.recruitmentexperience.data")
public class RecruitmentExperienceApplication implements CommandLineRunner {

    @Resource
    DocumentService documentService;

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentExperienceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }

    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new io.sentry.spring.SentryExceptionResolver();
    }

    @Override
    public void run(String... args) throws Exception {
        documentService.deleteAll();
        documentService.init();
    }
}
