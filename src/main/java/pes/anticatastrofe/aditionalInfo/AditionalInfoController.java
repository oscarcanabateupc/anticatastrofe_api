package pes.anticatastrofe.aditionalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.admin.Admin;
import pes.anticatastrofe.admin.AdminDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/aditional_info")

public class AditionalInfoController {
    private final AditionalInfoService aditionalInfoService;

    @Autowired
    public AditionalInfoController(AditionalInfoService aditionalInfoService) {
        this.aditionalInfoService = aditionalInfoService;
    }

    @GetMapping
    public List<AditionalInfoDTO> getAditionalInfos() {
        List<AditionalInfo> users = aditionalInfoService.getAditionalInfos();
        List<AditionalInfoDTO> aditionalInfoDTOS = users.stream()
                .map(aditionalInfo->new AditionalInfoDTO(aditionalInfo))
                .collect(Collectors.toList());
        return aditionalInfoDTOS;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewAditionalInfo(@RequestBody AditionalInfo aditionalInfo){
        Map<String, String> response = new HashMap<String, String>();
        if(!aditionalInfoService.findByID(aditionalInfo.email).isPresent()) {
            AditionalInfo ai = aditionalInfoService.addNewAditionalInfo(aditionalInfo);
            response.put("operation_success", "true");
            response.put("deleted_object_id", ai.email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("message", "aditionalInfo already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public Map<String,String> deleteAditionalInfo(@RequestParam String email) {
        aditionalInfoService.deleteAditionalInfo(email);
        Map<String, String> map = new HashMap<String, String>();
        map.put("operation_success", "true");
        map.put("deleted_person_id",email);
        return map;
    }
}
