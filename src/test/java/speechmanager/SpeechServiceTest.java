package speechmanager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class SpeechServiceTest {

    @InjectMocks
    private SpeechService speechService;

    @Mock
    private SpeechRepository speechRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSpeechesSuccess() {
        Speech speech1 = new Speech("Text1", "Alice", "key1", LocalDate.now());
        Speech speech2 = new Speech("Text2", "Bob", "key2", LocalDate.now());
        when(speechRepository.findAll()).thenReturn(Arrays.asList(speech1, speech2));

        assertEquals(2, speechService.getAllSpeeches().size());
        verify(speechRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllSpeechesEmpty() {
        when(speechRepository.findAll()).thenReturn(Collections.emptyList());

        assertTrue(speechService.getAllSpeeches().isEmpty());
        verify(speechRepository, times(1)).findAll();
    }

    @Test
    public void testAddSpeechSuccess() {
        Speech speech = new Speech("New Text", "Alice", "keywords", LocalDate.now());
        when(speechRepository.save(speech)).thenReturn(speech);

        Speech created = speechService.addSpeech(speech);
        assertNotNull(created);
        assertEquals("New Text", created.getText());
        verify(speechRepository, times(1)).save(speech);
    }

    @Test
    public void testAddSpeechWithNullFields() {
        Speech speech = new Speech(null, null, null, null);
        when(speechRepository.save(speech)).thenReturn(speech);

        Speech created = speechService.addSpeech(speech);
        assertNull(created.getText());
        verify(speechRepository, times(1)).save(speech);
    }

    @Test
    public void testUpdateSpeechSuccess() throws Exception {
        Speech existing = new Speech("Old Text", "Alice", "old, key", LocalDate.now().minusDays(1));
        existing.setId(1L);
        Speech updated = new Speech("New Text", "Bob", "new, key", LocalDate.now());
        when(speechRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(speechRepository.save(any(Speech.class))).thenReturn(existing);

        Speech result = speechService.updateSpeach(1L, updated);
        assertEquals("New Text", result.getText());
        assertEquals("Bob", result.getAuthor());
        verify(speechRepository, times(1)).findById(1L);
        verify(speechRepository, times(1)).save(existing);
    }

    @Test(expected = Exception.class)
    public void testUpdateSpeechNotFound() throws Exception {
        Speech updated = new Speech("New Text", "Bob", "new, key", LocalDate.now());
        when(speechRepository.findById(1L)).thenReturn(Optional.empty());

        speechService.updateSpeach(1L, updated);
        verify(speechRepository, times(1)).findById(1L);
        verify(speechRepository, never()).save(any());
    }

    @Test
    public void testUpdateSpeechWithNullInput() throws Exception {
        Speech existing = new Speech("Old Text", "Alice", "old, key", LocalDate.now());
        existing.setId(1L);
        when(speechRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(speechRepository.save(any(Speech.class))).thenReturn(existing);

        Speech result = speechService.updateSpeach(1L, new Speech(null, null, null, null));
        assertNull(result.getText());
        assertNull(result.getAuthor());
        assertNull(result.getKeywords());
        assertNull(result.getSpeechDate());
        verify(speechRepository, times(1)).save(existing);
    }
}