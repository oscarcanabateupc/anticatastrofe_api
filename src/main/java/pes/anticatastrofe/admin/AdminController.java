package pes.anticatastrofe.admin;


import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.aditionalInfo.AditionalInfoService;
import pes.anticatastrofe.person.PersonService;
import pes.anticatastrofe.tag.TagDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/admin")

public class AdminController {
    private final AdminService adminService;
    private final PersonService personService;
    private final AditionalInfoService aditionalInfoService;

    @Autowired
    public AdminController(AdminService adminService, PersonService personService, AditionalInfoService aditionalInfoService) {
        this.adminService = adminService;
        this.personService = personService;
        this.aditionalInfoService = aditionalInfoService;
    }

    @ApiResponse(description = "Success",responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = AdminDTO.class))))
    @GetMapping
    public List<AdminDTO> getAdmins() {
        List<Admin> users = adminService.getAdmins();
        return users.stream()
                .map(AdminDTO::new)
                .collect(Collectors.toList());
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = AdminDTO.class))),
            @ApiResponse(description = "Duplicated object", responseCode = "208", content = @Content(schema = @Schema(hidden = true)))}
    )
    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewAdmin(@RequestBody Admin admin) {
        Map<String, String> response = new HashMap<>();
        if (!adminService.findByID(admin.getEmail()).isPresent()) {
            personService.addNewPerson(admin.getPerson());
            Admin a = adminService.addNewAdmin(admin);
            response.put("operation_success", "true");
            response.put("created_object_id", a.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new DuplicateKeyException("");
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteAdmin(@RequestParam String email) {
        Map<String, String> response = new HashMap<>();
        if (adminService.findByID(email).isPresent()) {
            personService.deletePerson(email);
            adminService.deleteAdmin(email);
            if (aditionalInfoService.findByID(email).isPresent()) aditionalInfoService.deleteAditionalInfo(email);
            response.put("operation_success", "true");
            response.put("deleted_person_id", email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new EmptyResultDataAccessException(1);
    }
}
