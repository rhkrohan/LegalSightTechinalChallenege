package speechmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/speeches")
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    // Retrieve all speeches
    @GetMapping
    public ResponseEntity<List<Speech>> getAllSpeeches() {
        List<Speech> speeches = speechService.getAllSpeeches();
        return new ResponseEntity<>(speeches, HttpStatus.OK);
    }

    // Add a new speech
    @PostMapping
    public ResponseEntity<Speech> addSpeech(@RequestBody Speech speech) {
        Speech createdSpeech = speechService.addSpeech(speech);
        return new ResponseEntity<>(createdSpeech, HttpStatus.CREATED);
    }

    // Update an existing speech 
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSpeach(@PathVariable Long id, @RequestBody Speech speech) {
        try {
            Speech updatedSpeech = speechService.updateSpeach(id, speech);
            return new ResponseEntity<>(updatedSpeech, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Setter for speechService (useful for injecting mocks during testing)
    public void setSpeechService(SpeechService speechService) {
        this.speechService = speechService;
    }
}
