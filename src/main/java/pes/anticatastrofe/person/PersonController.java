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
    public Map<String, String> userPasswordMatch(@RequestParam String email, @RequestParam String introduced_password) {
        Map<String, String> map = new HashMap<>();
        if (personService.userPasswordMatch(email, introduced_password)) map.put("login_success", "true");
        else map.put("login_success", "false");
        return map;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewPerson(@RequestBody Person person) {
        Map<String, String> response = new HashMap<>();
        if (!personService.findByID(person.email).isPresent()) {
            Person p = personService.addNewPerson(person);
            response.put("operation_success", "true");
            response.put("deleted_object_id", p.email);
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
            personService.deleteAditionalInfo(email);
            response.put("operation_success", "true");
            response.put("deleted_person_id", email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "person not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
