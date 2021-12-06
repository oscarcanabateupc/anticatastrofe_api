package pes.anticatastrofe.landmark;

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
import pes.anticatastrofe.tag.Tag;
import pes.anticatastrofe.tag.TagDTO;
import pes.anticatastrofe.tag.TagService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/landmark")
public class LandmarkController {
    private final LandmarkService landmarkService;
    private final PersonService personService;
    private final TagService tagService;

    @Autowired
    public LandmarkController(LandmarkService landmarkService, PersonService personService,TagService tagService) {
        this.landmarkService = landmarkService;
        this.personService = personService;
        this.tagService = tagService;
    }

    @ApiResponse(description = "Success",responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = LandmarkDTO.class))))
    @GetMapping
    public List<LandmarkDTO> getLandmarks() {
        List<Landmark> landmarks = landmarkService.getLandmarks();
        return landmarks.stream()
                .map(LandmarkDTO::new)
                .collect(Collectors.toList());
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = LandmarkDTO.class))),
            @ApiResponse(description = "Duplicated object", responseCode = "208", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "Failed dependency on object", responseCode = "424", content = @Content(schema = @Schema(hidden = true)))}
    )
    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewLandmark(@RequestBody LandMarkDTOin landMarkDTOin) {
        Optional<Person> creator = personService.findByID(landMarkDTOin.getCreator_email());
        Optional<Tag> tag = tagService.findByID(landMarkDTOin.getTag_name());
        if (!creator.isPresent()) throw new DataIntegrityViolationException("");
        if (!tag.isPresent()) throw new DataIntegrityViolationException("");
        if (landmarkService.getLandmarkById(landMarkDTOin.getId()).isPresent()) throw new DuplicateKeyException("");

        Landmark l = new Landmark(landMarkDTOin,creator.get(),tag.get());
        l = landmarkService.addNewLandmark(l);
        Map<String, String> response = new HashMap<>();
        response.put("operation_success", "true");
        response.put("new_landmark_id", Integer.toString(l.getId()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteLandmark(@RequestParam Integer landmark_id) {
        Map<String, String> response = new HashMap<>();
        if (landmarkService.findByID(landmark_id).isPresent()) {
            landmarkService.deleteLandmark(landmark_id);
            response.put("operation_success", "true");
            response.put("deleted_landmark_id", Integer.toString(landmark_id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new EmptyResultDataAccessException(1);
    }
}