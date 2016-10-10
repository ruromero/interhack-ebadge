package eu.europa.ec.interhack.ebadge.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by rromero on 10/10/16.
 */
@SpringBootApplication
@ComponentScan("eu.europa.ec.interhack.ebadge")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
