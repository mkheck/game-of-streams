package com.thehecklers.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

@SpringBootApplication
public class SourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceApplication.class, args);
    }

}

@Configuration
@EnableScheduling
@AllArgsConstructor
class CoffeeGrower {
    private final CoffeeGenerator generator;

    @Bean
    Supplier<WholesaleCoffee> sendCoffee() {
        return () -> generator.generate();
    }
}

@Component
class CoffeeGenerator {
    private final List<String> names = List.of("Kaldi's", "Tim Hortons", "Espresso Roast", "Café Cereza");
    private final Random rnd = new Random();

    WholesaleCoffee generate() {
    	return new WholesaleCoffee(UUID.randomUUID().toString(),
				names.get(rnd.nextInt(names.size())));
	}
}

@Data
@AllArgsConstructor
class WholesaleCoffee {
    private final String id, name;
}