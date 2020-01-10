package com.thehecklers.sink;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@SpringBootApplication
public class SinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SinkApplication.class, args);
    }

}

@Configuration
class CoffeeDrinker {
	@Bean
	Consumer<RetailCoffee> drinkIt() {
		return System.out::println;
	}
}

@Data
@AllArgsConstructor
class RetailCoffee {
    enum State {
        WHOLE_BEAN,
        GROUND
    }

    private final String id, name;
    private final State state;
}