package speechmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeechRepository extends JpaRepository<Speech, Long> {
    // Basic CRUD operations are provided by JpaRepository.
}
