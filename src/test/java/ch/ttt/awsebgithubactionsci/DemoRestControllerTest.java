package ch.ttt.awsebgithubactionsci;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class DemoRestControllerTest {

    @Autowired
    DemoRestController demoRestController;

    @Test
    void healthCheck() {
        assertThat(demoRestController.healthCheck(), is("OK"));
    }

    @Test
    void nations() {
        assertThat(demoRestController.nations(), contains(
                "Switzerland",
                "Australia",
                "Israel",
                "Mexico"
        ));
    }
}
