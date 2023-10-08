package squareonex.evetracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles({"simpleBootstrapData", "jpa"})
@ExtendWith(SpringExtension.class)
class EveTrackerApplicationTest {
    @Test
    public void contextLoads() {

    }
}