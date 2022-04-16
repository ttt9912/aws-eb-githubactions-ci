package ch.ttt.awsebgithubactionsci;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/*
 * Delete if it causes problems
 */
@Slf4j
@RestController
public class EC2MetadataRestController {
    private final HttpClient client = HttpClient.newHttpClient();

    @GetMapping("/info")
    public List<String> info() throws IOException, InterruptedException {
        final String token = requestToken();

        return List.of(
                String.format("Hostname: %s", httpGet(token, "http://169.254.169.254/latest/meta-data/hostname")),
                String.format("Public: %s", httpGet(token, "http://169.254.169.254/latest/meta-data/public-hostname")),
                String.format("AZ: %s", httpGet(token, "http://169.254.169.254/latest/meta-data/placement/availability-zone"))
        );
    }

    private String httpGet(final String token, final String url) throws IOException, InterruptedException {
        return client.send(
                        HttpRequest.newBuilder(URI.create(url))
                                .header("X-aws-ec2-metadata-token", token)
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                .body();
    }

    private String requestToken() throws IOException, InterruptedException {
        return client.send(
                        HttpRequest.newBuilder(URI.create("http://169.254.169.254/latest/api/token"))
                                .header("X-aws-ec2-metadata-token-ttl-seconds", "21600")
                                .PUT(HttpRequest.BodyPublishers.noBody())
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                .body();
    }
}
