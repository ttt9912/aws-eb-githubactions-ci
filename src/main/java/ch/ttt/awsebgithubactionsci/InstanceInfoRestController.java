package ch.ttt.awsebgithubactionsci;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/*
 * Delete if it causes problems
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class InstanceInfoRestController {
    private final RestTemplate restTemplate;

    @GetMapping("/info")
    public String healthCheck() {
        final String token = restTemplate.exchange(
                "http://169.254.169.254/latest/api/token",
                HttpMethod.PUT,
                new HttpEntity<>(Map.of("X-aws-ec2-metadata-token-ttl-seconds", 21600)),
                String.class
        ).getBody();

        final String az = restTemplate.exchange(
                "http://169.254.169.254/latest/meta-data/placement/availability-zone",
                HttpMethod.GET,
                new HttpEntity<>(Map.of("X-aws-ec2-metadata-token", token)),
                String.class
        ).getBody();

        return String.format("AZ: %s", az);
    }
}
