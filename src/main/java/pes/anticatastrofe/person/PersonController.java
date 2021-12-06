package pes.anticatastrofe.person;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import pes.anticatastrofe.tag.TagDTO;

import javax.security.auth.login.CredentialException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/person")

public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ApiResponse(description = "Success",responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))))
    @GetMapping(value = "/persons")
    public List<PersonDTO> getPersons() {
        List<Person> persons = personService.getPersons();
        return persons.stream()
                .map(PersonDTO::new)
                .collect(Collectors.toList());
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Bad credentials, no success for you", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
    @GetMapping(value = "/userPasswordMatch")
    public ResponseEntity<Map<String, String>> userPasswordMatch(@RequestParam String email, @RequestParam String introduced_password) throws CredentialException {
        Map<String, String> response = new HashMap<>();
        if (personService.findByID(email).isPresent()) {
            if (personService.userPasswordMatch(email, introduced_password)) {
                response.put("login_success", "true");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else throw new CredentialException("");
        } else throw new EmptyResultDataAccessException(1);
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Bad credentials, no token for you", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
    @GetMapping(value = "/getToken")
    public ResponseEntity<Map<String, String>> getToken(@RequestParam String email, @RequestParam String introduced_password) throws CredentialException {
        Map<String, String> response = new HashMap<>();
        if (personService.findByID(email).isPresent()) {
            if (personService.userPasswordMatch(email, introduced_password)) {
                String token = personService.getToken(email);
                response.put("operation_success", "true");
                response.put("token", token);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else throw new CredentialException("");
        } else throw new EmptyResultDataAccessException(1);
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
    @PutMapping(value = "/resetToken")
    public ResponseEntity<Map<String, String>> resetToken(@RequestParam String email, @RequestParam String introduced_token) {
        Map<String, String> response = new HashMap<>();
        if (personService.findByID(email).isPresent()) {
            personService.resetToken(email, introduced_token);
            response.put("operation_success", "true");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new EmptyResultDataAccessException(1);
    }
}
