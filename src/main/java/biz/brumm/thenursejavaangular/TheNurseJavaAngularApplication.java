package biz.brumm.thenursejavaangular;

import biz.brumm.thenursejavaangular.entity.IUserRepository;
import biz.brumm.thenursejavaangular.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class TheNurseJavaAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheNurseJavaAngularApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IUserRepository userRepository) {
		return args -> {
			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
				User user = new User(name, name.toLowerCase() + "@domain.com");
				userRepository.save(user);
			});
			userRepository.findAll().forEach(System.out::println);
		};
	}

}
