package pes.anticatastrofe.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/person")

public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAdmins() {
        return personService.getPersons();
    }

    @PostMapping
    public void registerNewAdmin(@RequestBody Person person){
        personService.addNewPerson(person);
    }

    @DeleteMapping
    public void deleteAdmin(@RequestParam String email) {
        personService.deleteAditionalInfo(email);

    }
}
