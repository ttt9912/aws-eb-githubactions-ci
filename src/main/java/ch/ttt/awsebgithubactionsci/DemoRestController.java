package ch.ttt.awsebgithubactionsci;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DemoRestController {

    @GetMapping("/")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/nations")
    public List<String> nations() {
        log.info("requesting nations...");
        return List.of(
                "Switzerland",
                "Australia",
                "Israel",
                "Mexico"
        );
    }

    @GetMapping("/exception")
    public String throwError() {
        throw new RuntimeException();
    }
}
