package nl.hartenjagen.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/health")
    public String getHealthCheck() {
        return "yay";
    }
}
