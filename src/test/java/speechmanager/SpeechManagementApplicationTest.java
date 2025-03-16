package speechmanager;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class SpeechManagementApplicationTest {

    @Test
    public void testApplicationStarts() {
        try (ConfigurableApplicationContext context = SpringApplication.run(SpeechManagementApplication.class, new String[]{})) {
            assertNotNull(context);
        }
    }
}