package pes.anticatastrofe.landmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pes.anticatastrofe.pin.Pin;
import pes.anticatastrofe.pin.PinService;
import pes.anticatastrofe.tag.Tag;
import pes.anticatastrofe.tag.TagService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/landmark")
public class LandmarkController {
    private final LandmarkService landmarkService;
    private final TagService tagService;

    @Autowired
    public LandmarkController(LandmarkService landmarkService,TagService tagService) {
        this.landmarkService = landmarkService;
        this.tagService = tagService;
    }

    @GetMapping
    public List<LandmarkDTO> getLandmarks() {
        List<Landmark> landmarks = landmarkService.getLandmarks();
        List<LandmarkDTO> landmarksDTO = landmarks.stream()
                .map(landmark->new LandmarkDTO(landmark))
                .collect(Collectors.toList());
        return landmarksDTO;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewLandmark(@RequestBody Landmark landmark){
        Map<String, String> response = new HashMap<String, String>();
        if (tagService.getTagByName(landmark.tag).isPresent()) {
            Landmark l = landmarkService.addNewLandmark(landmark);
            response.put("operation_success", "true");
            response.put("new_landmark_id", Integer.toString(l.id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("message", "tag not exists");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public Map<String,String> deleteLandmark(@RequestParam Integer landmark_id) {
        landmarkService.deleteLandmark(Integer.toString(landmark_id));
        Map<String, String> map = new HashMap<String, String>();
        map.put("operation_success", "true");
        map.put("deleted_landmark_id",Integer.toString(landmark_id));
        return map;
    }
}