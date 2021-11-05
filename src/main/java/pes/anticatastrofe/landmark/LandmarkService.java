package pes.anticatastrofe.landmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pes.anticatastrofe.pin.Pin;
import pes.anticatastrofe.pin.PinRepository;

import java.util.List;

@Service
public class LandmarkService {
    private final LandmarkRepository landmarkRepository;

    @Autowired
    public LandmarkService(LandmarkRepository landmarkRepository) {
        this.landmarkRepository = landmarkRepository;
    }

    public List<Landmark> getLandmarks() {
        return landmarkRepository.findAll();
    }

    public Landmark addNewLandmark(Landmark landmark) {
        return landmarkRepository.save(landmark);
    }

    public void deleteLandmark(String landmark_id) {
        landmarkRepository.deleteById(landmark_id);
    }
}
