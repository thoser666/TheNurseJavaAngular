package biz.brumm.thenursejavaangular;

import biz.brumm.thenursejavaangular.entity.Mandant;
import biz.brumm.thenursejavaangular.repository.IMandantRepository;
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
	CommandLineRunner init(IMandantRepository userRepository) {
		return args -> {
			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
				Mandant mandant = new Mandant(name, name.toLowerCase() + "@domain.com");
				userRepository.save(mandant);
			});
			userRepository.findAll().forEach(System.out::println);
		};
	}

}
