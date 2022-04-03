package ch.ttt.awsebgithubactionsci;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoRestController {

    @GetMapping("/")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/version")
    public String version() {
        return "The actual version is 1.0.0";
    }

    @GetMapping("/nations")
    public List<String> nations() {
        return List.of(
                "Switzerland",
                "Australia",
                "Israel",
                "Mexico"
        );
    }
}
