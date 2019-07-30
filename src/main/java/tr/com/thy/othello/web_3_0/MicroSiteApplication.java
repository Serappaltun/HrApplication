package tr.com.thy.othello.web_3_0;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MicroSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroSiteApplication.class, args);
	}

}
