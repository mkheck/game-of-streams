package com.thehecklers.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.function.Function;

@SpringBootApplication
public class ProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }

}

@Configuration
class CoffeeRoaster {
    private final Random rnd = new Random();

    @Bean
    Function<WholesaleCoffee, RetailCoffee> processIt() {
        return wCoffee -> {
            var rCoffee = new RetailCoffee(wCoffee.getId(),
                    wCoffee.getName(),
                    rnd.nextInt(2) == 0
                            ? RetailCoffee.State.WHOLE_BEAN
                            : RetailCoffee.State.GROUND);

//            System.out.println(rCoffee);

            return rCoffee;
        };
    }

    @Bean
    Function<RetailCoffee, RetailCoffee> fixIt() {
        return inCoffee -> {
            var outCoffee = new RetailCoffee(inCoffee.getId(),
                    inCoffee.getName(),
                    RetailCoffee.State.WHOLE_BEAN);

            System.out.println(outCoffee);

            return outCoffee;
        };
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

@Data
@AllArgsConstructor
class WholesaleCoffee {
    private final String id, name;
}