package com.github.piyushpatel2005;

import com.github.piyushpatel2005.customer.Customer;
import com.github.piyushpatel2005.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer alice = new Customer("Alice", "alice@gmail.com", 23);
            Customer jamila = new Customer("Jamila", "jamila@microsoft.com", 21);
            List<Customer> customers = List.of(alice, jamila);
            customerRepository.saveAll(customers);
        };
    }
}
