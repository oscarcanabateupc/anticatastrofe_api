package pes.anticatastrofe.aditionalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/aditional_info")

public class AditionalInfoController {
    private final AditionalInfoService aditionalInfoService;

    @Autowired
    public AditionalInfoController(AditionalInfoService aditionalInfoService) {
        this.aditionalInfoService = aditionalInfoService;
    }

    @GetMapping
    public List<AditionalInfo> getAditionalInfos() {
        return aditionalInfoService.getAditionalInfos();
    }

    @PostMapping
    public void registerNewAditionalInfo(@RequestBody AditionalInfo aditionalInfo){
        aditionalInfoService.addNewAditionalInfo(aditionalInfo);
    }

    @DeleteMapping
    public void deleteAditionalInfo(@RequestParam String email) {
        aditionalInfoService.deleteAditionalInfo(email);

    }
}
