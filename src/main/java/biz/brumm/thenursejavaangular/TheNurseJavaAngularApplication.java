package biz.brumm.thenursejavaangular;

import biz.brumm.thenursejavaangular.entity.Mandant;
import biz.brumm.thenursejavaangular.repository.IMandantRepository;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@Log
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

			// create a loop for all items in userRepository.findAll()
			// and print them using a logger
            for (Mandant mandant : userRepository.findAll()) {
                log.info("Iter= " + mandant.toString());
            }

		};
	}

}
