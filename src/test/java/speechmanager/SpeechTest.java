package speechmanager;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class SpeechTest {

    @Test
    public void testDefaultConstructorAndSetters() {
        Speech speech = new Speech();
        speech.setId(1L);
        speech.setText("Hello World");
        speech.setAuthor("Alice");
        speech.setKeywords("greeting, world");
        LocalDate date = LocalDate.now();
        speech.setSpeechDate(date);

        assertEquals(Long.valueOf(1L), speech.getId());
        assertEquals("Hello World", speech.getText());
        assertEquals("Alice", speech.getAuthor());
        assertEquals("greeting, world", speech.getKeywords());
        assertEquals(date, speech.getSpeechDate());
    }

    @Test
    public void testParameterizedConstructor() {
        LocalDate date = LocalDate.now();
        Speech speech = new Speech("Parameterized Text", "Bob", "param, test", date);

        assertNull(speech.getId());
        assertEquals("Parameterized Text", speech.getText());
        assertEquals("Bob", speech.getAuthor());
        assertEquals("param, test", speech.getKeywords());
        assertEquals(date, speech.getSpeechDate());
    }

    @Test
    public void testSetId() {
        Speech speech = new Speech();
        speech.setId(2L);
        assertEquals(Long.valueOf(2L), speech.getId());
    }

    @Test
    public void testSetText() {
        Speech speech = new Speech();
        speech.setText("New Text");
        assertEquals("New Text", speech.getText());
    }

    @Test
    public void testSetAuthor() {
        Speech speech = new Speech();
        speech.setAuthor("Charlie");
        assertEquals("Charlie", speech.getAuthor());
    }

    @Test
    public void testSetKeywords() {
        Speech speech = new Speech();
        speech.setKeywords("new, keywords");
        assertEquals("new, keywords", speech.getKeywords());
    }

    @Test
    public void testSetSpeechDate() {
        Speech speech = new Speech();
        LocalDate date = LocalDate.of(2023, Month.OCTOBER, 1);
        speech.setSpeechDate(date);
        assertEquals(date, speech.getSpeechDate());
    }

    @Test
    public void testSetTextToNull() {
        Speech speech = new Speech();
        speech.setText(null);
        assertNull(speech.getText());
    }

    @Test
    public void testSetAuthorToNull() {
        Speech speech = new Speech();
        speech.setAuthor(null);
        assertNull(speech.getAuthor());
    }

    @Test
    public void testSetKeywordsToNull() {
        Speech speech = new Speech();
        speech.setKeywords(null);
        assertNull(speech.getKeywords());
    }

    @Test
    public void testSetSpeechDateToNull() {
        Speech speech = new Speech();
        speech.setSpeechDate(null);
        assertNull(speech.getSpeechDate());
    }

    @Test
    public void testSetIdToNull() {
        Speech speech = new Speech();
        speech.setId(null);
        assertNull(speech.getId());
    }

    @Test
    public void testParameterizedConstructorWithNullValues() {
        Speech speech = new Speech(null, null, null, null);
        assertNull(speech.getId());
        assertNull(speech.getText());
        assertNull(speech.getAuthor());
        assertNull(speech.getKeywords());
        assertNull(speech.getSpeechDate());
    }

    @Test
    public void testParameterizedConstructorWithEmptyStrings() {
        LocalDate date = LocalDate.now();
        Speech speech = new Speech("", "", "", date);
        assertNull(speech.getId());
        assertEquals("", speech.getText());
        assertEquals("", speech.getAuthor());
        assertEquals("", speech.getKeywords());
        assertEquals(date, speech.getSpeechDate());
    }

    @Test
    public void testParameterizedConstructorWithBoundaryValues() {
        LocalDate date = LocalDate.of(1970, Month.JANUARY, 1); // Unix epoch start
        Speech speech = new Speech("A", "B", "C", date);
        assertNull(speech.getId());
        assertEquals("A", speech.getText());
        assertEquals("B", speech.getAuthor());
        assertEquals("C", speech.getKeywords());
        assertEquals(date, speech.getSpeechDate());
    }
}