package speechmanager;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class SpeechControllerTest {

    private MockMvc mockMvc;
    private SpeechService speechService;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        speechService = mock(SpeechService.class);
        SpeechController speechController = new SpeechController();
        speechController.setSpeechService(speechService);
        mockMvc = MockMvcBuilders.standaloneSetup(speechController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    public void testGetAllSpeechesSuccess() throws Exception {
        Speech speech1 = new Speech("Text1", "Alice", "key1", LocalDate.now());
        Speech speech2 = new Speech("Text2", "Bob", "key2", LocalDate.now());
        when(speechService.getAllSpeeches()).thenReturn(Arrays.asList(speech1, speech2));

        mockMvc.perform(get("/api/speeches")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].author").value("Alice"))
                .andExpect(jsonPath("$[1].author").value("Bob"));
        verify(speechService, times(1)).getAllSpeeches();
    }

    @Test
    public void testGetAllSpeechesEmpty() throws Exception {
        when(speechService.getAllSpeeches()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/speeches")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
        verify(speechService, times(1)).getAllSpeeches();
    }

    @Test
    public void testAddSpeechSuccess() throws Exception {
        Speech speech = new Speech("New Text", "Charlie", "new, key", LocalDate.now());
        Speech savedSpeech = new Speech("New Text", "Charlie", "new, key", LocalDate.now());
        savedSpeech.setId(1L);
        when(speechService.addSpeech(any(Speech.class))).thenReturn(savedSpeech);

        String speechJson = objectMapper.writeValueAsString(speech);

        mockMvc.perform(post("/api/speeches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(speechJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.author").value("Charlie"));
        verify(speechService, times(1)).addSpeech(any(Speech.class));
    }

    @Test
    public void testAddSpeechWithNullFields() throws Exception {
        Speech speech = new Speech(null, null, null, null);
        Speech savedSpeech = new Speech(null, null, null, null);
        savedSpeech.setId(1L);
        when(speechService.addSpeech(any(Speech.class))).thenReturn(savedSpeech);

        String speechJson = objectMapper.writeValueAsString(speech);

        mockMvc.perform(post("/api/speeches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(speechJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.text").doesNotExist()); // Null fields are omitted
        verify(speechService, times(1)).addSpeech(any(Speech.class));
    }

    @Test
    public void testUpdateSpeechSuccess() throws Exception {
        Long id = 1L;
        Speech updatedSpeech = new Speech("Updated Text", "Dave", "updated, key", LocalDate.now());
        updatedSpeech.setId(id);
        when(speechService.updateSpeach(eq(id), any(Speech.class))).thenReturn(updatedSpeech);

        String updatedJson = objectMapper.writeValueAsString(updatedSpeech);

        mockMvc.perform(put("/api/speeches/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.text").value("Updated Text"));
        verify(speechService, times(1)).updateSpeach(eq(id), any(Speech.class));
    }

    @Test
    public void testUpdateSpeechNotFound() throws Exception {
        Long id = 999L;
        Speech updatedSpeech = new Speech("Updated Text", "Eve", "updated, key", LocalDate.now());
        when(speechService.updateSpeach(eq(id), any(Speech.class)))
                .thenThrow(new Exception("Speech not found with id: " + id));

        String updatedJson = objectMapper.writeValueAsString(updatedSpeech);

        mockMvc.perform(put("/api/speeches/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedJson))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Speech not found with id: " + id));
        verify(speechService, times(1)).updateSpeach(eq(id), any(Speech.class));
    }

    @Test
    public void testUpdateSpeechWithInvalidJson() throws Exception {
        Long id = 1L;
        mockMvc.perform(put("/api/speeches/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{invalid json}"))
                .andExpect(status().isBadRequest());
        verify(speechService, never()).updateSpeach(any(), any());
    }
}