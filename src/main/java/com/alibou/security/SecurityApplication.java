package com.alibou.security;

import com.alibou.security.auth.AuthenticationService;
import com.alibou.security.auth.RegisterRequest;
import com.alibou.security.user.Role;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.alibou.security.user.Role.*;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper (){
		return new ModelMapper();
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Ruben")
					.lastname("Mm")
					.email("RM23@mail.com")
					.password("password")
					.mobile("+27703882569")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var content_creator = RegisterRequest.builder()
					.firstname("Shiz")
					.lastname("Itu")
					.email("SI20@mail.com")
					.password("password")
					.mobile("+27830053939")
					.role(CONTENT_CREATOR)
					.build();
			System.out.println("Content Creator token: " + service.register(content_creator).getAccessToken());

		};
	}
}
