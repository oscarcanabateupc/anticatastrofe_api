package pes.anticatastrofe.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/persons")
    public List<PersonDTO> getPersons() {
        List<Person> persons = personService.getPersons();
        return persons.stream()
                .map(PersonDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/userPasswordMatch")
    public ResponseEntity<Map<String, String>> userPasswordMatch(@RequestParam String email, @RequestParam String introduced_password) {
        Map<String, String> response = new HashMap<>();
        if (personService.findByID(email).isPresent()) {
            if (personService.userPasswordMatch(email, introduced_password)) {
                response.put("login_success", "true");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("login_success", "false");
                response.put("message", "incorrect password, no login for you");
                response.put("status", HttpStatus.FORBIDDEN.toString());
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }
        } else {
            response.put("login_success", "false");
            response.put("message", "person not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getToken")
    public ResponseEntity<Map<String, String>> getToken(@RequestParam String email, @RequestParam String introduced_password) {
        Map<String, String> response = new HashMap<>();
        if (personService.findByID(email).isPresent()) {
            if (personService.userPasswordMatch(email, introduced_password)) {
                String token = personService.getToken(email);
                response.put("operation_success", "true");
                response.put("token", token);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "incorrect password, no token for you");
                response.put("status", HttpStatus.FORBIDDEN.toString());
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }
        } else {
            response.put("message", "person not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/resetToken")
    public ResponseEntity<Map<String, String>> resetToken(@RequestParam String email, @RequestParam String introduced_token) {
        Map<String, String> response = new HashMap<>();
        if (personService.findByID(email).isPresent()) {
            personService.resetToken(email, introduced_token);
            response.put("operation_success", "true");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "person not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewPerson(@RequestBody Person person) {
        Map<String, String> response = new HashMap<>();
        if (!personService.findByID(person.getEmail()).isPresent()) {
            Person p = personService.addNewPerson(person);
            response.put("operation_success", "true");
            response.put("new_object_id", p.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "person already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deletePerson(@RequestParam String email) {
        Map<String, String> response = new HashMap<>();
        if (personService.findByID(email).isPresent()) {
            personService.deletePerson(email);
            response.put("operation_success", "true");
            response.put("deleted_object_id", email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "person not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
