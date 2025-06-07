package net.yassir.msdiayassirspringmvc;

import net.yassir.msdiayassirspringmvc.entities.Product;
import net.yassir.msdiayassirspringmvc.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication
public class MsdiaYassirSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsdiaYassirSpringMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("Computer")
                    .price(5460)
                    .quantity(12)
                    .build());
            productRepository.save(Product.builder()
                    .name("printer")
                    .price(1200)
                    .quantity(11)
                    .build());
            productRepository.save(Product.builder()
                    .name("smart phone")
                    .price(4000)
                    .quantity(33)
                    .build());
            productRepository.findAll().forEach(p -> {
                System.out.println(p.toString());
            });
        };
    }

}
