package pes.anticatastrofe.aditionalInfo;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.person.PersonService;
import pes.anticatastrofe.tag.TagDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/aditional_info")

public class AditionalInfoController {
    private final AditionalInfoService aditionalInfoService;
    private final PersonService personService;

    @Autowired
    public AditionalInfoController(AditionalInfoService aditionalInfoService, PersonService personService) {
        this.aditionalInfoService = aditionalInfoService;
        this.personService = personService;
    }

    @ApiResponse(description = "Success",responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = AditionalInfoDTO.class))))
    @GetMapping
    public List<AditionalInfoDTO> getAditionalInfos() {
        List<AditionalInfo> users = aditionalInfoService.getAditionalInfos();
        return users.stream()
                .map(AditionalInfoDTO::new)
                .collect(Collectors.toList());
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = AditionalInfoDTO.class))),
            @ApiResponse(description = "Duplicated object", responseCode = "208", content = @Content(schema = @Schema(hidden = true)))}
    )
    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewAditionalInfo(@RequestBody AditionalInfoDTOIn aditionalInfoDTOIn) {
        Map<String, String> response = new HashMap<>();
        Optional<Person> person = personService.findByID(aditionalInfoDTOIn.getEmail());
        Optional<AditionalInfo> aditional_info = aditionalInfoService.findByID(aditionalInfoDTOIn.getEmail());
        if (!person.isPresent()) throw new DataIntegrityViolationException("");
        if (aditional_info.isPresent()) throw new DuplicateKeyException("");

        Person p = person.get();
        AditionalInfo ai = new AditionalInfo(aditionalInfoDTOIn,p);
        ai = aditionalInfoService.addNewAditionalInfo(ai);
        response.put("operation_success", "true");
        response.put("deleted_object_id", ai.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteAditionalInfo(@RequestParam String email) {
        Map<String, String> response = new HashMap<>();
        if (aditionalInfoService.findByID(email).isPresent()) {
            aditionalInfoService.deleteAditionalInfo(email);
            response.put("operation_success", "true");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new EmptyResultDataAccessException(1);
    }
}
