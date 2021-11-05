package pes.anticatastrofe.aditionalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.admin.Admin;
import pes.anticatastrofe.admin.AdminDTO;

import java.util.List;
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
    public void registerNewAditionalInfo(@RequestBody AditionalInfo aditionalInfo){
        aditionalInfoService.addNewAditionalInfo(aditionalInfo);
    }

    @DeleteMapping
    public void deleteAditionalInfo(@RequestParam String email) {
        aditionalInfoService.deleteAditionalInfo(email);

    }
}
