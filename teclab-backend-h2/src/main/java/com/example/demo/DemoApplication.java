package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  org.springframework.boot.CommandLineRunner seed(UserRepository users, PasswordEncoder encoder) {
    return args -> {
      if (users.findByUsername("admin").isEmpty()) {
        User u = new User();
        u.setUsername("admin");
        u.setPassword(encoder.encode("admin123"));
        users.save(u);
      }
    };
  }
}
