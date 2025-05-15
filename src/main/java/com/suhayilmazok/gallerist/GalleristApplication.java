package com.suhayilmazok.gallerist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = {"com.suhayilmazok"})
@EntityScan(basePackages = {"com.suhayilmazok"})
@EnableJpaRepositories(basePackages = {"com.suhayilmazok"})
@SpringBootApplication
public class GalleristApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleristApplication.class, args);
    }

}
