package eu.europa.ec.interhack.ebadge.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rromero on 10/10/16.
 */
@RestController
public class HelloResource {

    @RequestMapping("/hello")
    public String greetings(@RequestParam(value="name", defaultValue = "world!") String name) {
        return "Hello " + name;
    }

}
