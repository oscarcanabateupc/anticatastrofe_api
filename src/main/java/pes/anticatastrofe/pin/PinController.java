package pes.anticatastrofe.pin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/pin")
public class PinController {
    private final PinService pinService;

    @Autowired
    public PinController(PinService pinService) {
        this.pinService = pinService;
    }

    @GetMapping
    public List<PinDTO> getPins() {
        List<Pin> pins = pinService.getPins();
        List<PinDTO> pinsDTO = pins.stream()
                .map(pin->new PinDTO(pin))
                .collect(Collectors.toList());
        return pinsDTO;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewPin(@RequestBody Pin pin){
        Map<String, String> response = new HashMap<String, String>();
        if(!pinService.findByID(pin.landmark_id).isPresent()) {
            Pin p = pinService.addNewPin(pin);
            response.put("operation_success", "true");
            response.put("new_object_id",Integer.toString(p.landmark_id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("message", "aditionalInfo already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public Map<String,String> deletePin(@RequestParam Integer landmark_id) {
        pinService.deletePin(landmark_id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("operation_success", "true");
        map.put("deleted_pin_id",Integer.toString(landmark_id));
        return map;
    }
}