package pes.anticatastrofe.pin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public void deletePin(String landmark_id) {
        pinRepository.deleteById(landmark_id);
    }
}
