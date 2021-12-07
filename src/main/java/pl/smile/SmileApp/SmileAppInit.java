package pl.smile.SmileApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
public class SmileAppInit {
	public static void main(String[] args) {
		SpringApplication.run(SmileAppInit.class, args);
	}

}
