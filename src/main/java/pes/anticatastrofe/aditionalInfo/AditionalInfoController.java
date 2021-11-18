package pes.anticatastrofe.aditionalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/aditional_info")

public class AditionalInfoController {
    private final AditionalInfoService aditionalInfoService;

    @Autowired
    public AditionalInfoController(AditionalInfoService aditionalInfoService) {
        this.aditionalInfoService = aditionalInfoService;
    }

    @GetMapping
    public List<AditionalInfoDTO> getAditionalInfos() {
        List<AditionalInfo> users = aditionalInfoService.getAditionalInfos();
        return users.stream()
                .map(AditionalInfoDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewAditionalInfo(@RequestBody AditionalInfo aditionalInfo) {
        Map<String, String> response = new HashMap<>();
        if (!aditionalInfoService.findByID(aditionalInfo.getEmail()).isPresent()) {
            AditionalInfo ai = aditionalInfoService.addNewAditionalInfo(aditionalInfo);
            response.put("operation_success", "true");
            response.put("deleted_object_id", ai.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "aditionalInfo already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteAditionalInfo(@RequestParam String email) {
        Map<String, String> response = new HashMap<>();
        if (aditionalInfoService.findByID(email).isPresent()) {
            aditionalInfoService.deleteAditionalInfo(email);
            response.put("operation_success", "true");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "aditionalInfo not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
