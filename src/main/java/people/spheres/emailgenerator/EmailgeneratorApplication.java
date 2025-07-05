package people.spheres.emailgenerator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
public class EmailgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailgeneratorApplication.class, args);
		System.out.println("Hello PeopleSpheres");
	}

	@Bean
	CommandLineRunner runner(Environment env) {
		return args -> {
			System.out.println(">>> ACTIVE PROFILE: " + Arrays.toString(env.getActiveProfiles()));
		};
	}

}
