package gr.aueb.cf.premierAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PremierApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PremierApiApplication.class, args);
	}

}
