package speechmanager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeechService {

    @Autowired
    private SpeechRepository speechRepository;

    // Retrieve all speeches
    public List<Speech> getAllSpeeches() {
        return speechRepository.findAll();
    }

    // Add a new speech
    public Speech addSpeech(Speech speech) {
        return speechRepository.save(speech);
    }

    // Edit an existing speech 
    public Speech updateSpeach(Long id, Speech updatedSpeech) throws Exception {
        Optional<Speech> optionalSpeech = speechRepository.findById(id);
        if (optionalSpeech.isPresent()) {
            Speech existingSpeech = optionalSpeech.get();
            existingSpeech.setText(updatedSpeech.getText());
            existingSpeech.setAuthor(updatedSpeech.getAuthor());
            existingSpeech.setKeywords(updatedSpeech.getKeywords());
            existingSpeech.setSpeechDate(updatedSpeech.getSpeechDate());
            return speechRepository.save(existingSpeech);
        } else {
            throw new Exception("Speech not found with id: " + id);
        }
    }
}
