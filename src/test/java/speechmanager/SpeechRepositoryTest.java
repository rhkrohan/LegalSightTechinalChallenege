package speechmanager;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpeechRepositoryTest {

    @Autowired
    private SpeechRepository speechRepository;

    @Test
    public void testSaveAndFindById() {
        Speech speech = new Speech("Test Text", "Test Author", "test, keys", LocalDate.now());
        Speech saved = speechRepository.save(speech);

        assertNotNull(saved.getId());
        Speech found = speechRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Test Text", found.getText());
    }

    @Test
    public void testFindAll() {
        Speech speech1 = new Speech("Text1", "Alice", "key1", LocalDate.now());
        Speech speech2 = new Speech("Text2", "Bob", "key2", LocalDate.now());
        speechRepository.save(speech1);
        speechRepository.save(speech2);

        List<Speech> speeches = speechRepository.findAll();
        assertEquals(2, speeches.size());
    }

    @Test
    public void testFindByIdNotFound() {
        assertFalse(speechRepository.findById(999L).isPresent());
    }
}