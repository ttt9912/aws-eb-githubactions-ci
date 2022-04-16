package ch.ttt.awsebgithubactionsci;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/*
 * Delete if it causes problems
 */
@Slf4j
@RestController
public class InstanceInfoRestController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/info")
    public String healthCheck() throws IOException, InterruptedException {
        // https://www.baeldung.com/java-9-http-client
        var client = HttpClient.newHttpClient();

        final String token = client.send(
                HttpRequest.newBuilder(URI.create("http://169.254.169.254/latest/api/token"))
                        .header("X-aws-ec2-metadata-token-ttl-seconds", "21600")
                        .PUT(HttpRequest.BodyPublishers.noBody())
                        .build(),
                        HttpResponse.BodyHandlers.ofString())
                .body();

        final String az = client.send(
                        HttpRequest.newBuilder(URI.create("http://169.254.169.254/latest/meta-data/placement/availability-zone"))
                                .header("X-aws-ec2-metadata-token", token)
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                .body();

        return String.format("AZ: %s", az);
    }
}
