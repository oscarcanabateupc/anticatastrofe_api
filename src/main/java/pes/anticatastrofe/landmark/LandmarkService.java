package pes.anticatastrofe.landmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteLandmark(Integer landmark_id) {
        landmarkRepository.deleteById(landmark_id);
    }

    public Optional<Landmark> getLandmarkById(Integer id) {
        return landmarkRepository.findById(id);
    }
}
