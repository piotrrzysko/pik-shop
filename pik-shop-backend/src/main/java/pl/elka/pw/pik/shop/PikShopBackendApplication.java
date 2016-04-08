package pl.elka.pw.pik.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = {"pl.elka.pw.pik.shop.domain.repository"})
@EntityScan(value = {"pl.elka.pw.pik.shop.domain"})
@ComponentScan(value = {"pl.elka.pw.pik.shop"})
public class PikShopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PikShopBackendApplication.class, args);
    }
}
