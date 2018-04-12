package pl.codeleak.samples.junit5.springboot1x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.codeleak.samples.shared.petclinic.repository.Owners;
import pl.codeleak.samples.shared.petclinic.repository.Pets;


@SpringBootApplication
public class SpringBoot1xApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot1xApp.class, args);
    }

    @Bean
    Pets pets() {
        return new Pets();
    }

    @Bean
    Owners owners() {
        return new Owners();
    }
}
