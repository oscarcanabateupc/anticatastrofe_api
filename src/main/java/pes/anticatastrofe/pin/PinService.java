package pes.anticatastrofe.pin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PinService {
    private final PinRepository pinRepository;

    @Autowired
    public PinService(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
    }

    public List<Pin> getPins() {
        return pinRepository.findAll();
    }

    public Pin addNewPin(Pin pin) {
        return pinRepository.save(pin);
    }

    public void deletePin(Integer landmark_id) {
        pinRepository.deleteById(landmark_id);
    }

    public Optional<Pin> findByID(int landmark_id) {
        return pinRepository.findById(landmark_id);
    }
}
