package pes.anticatastrofe.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/person")

public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/persons")
    public List<Person> getPersons() {
        return personService.getPersons();
    }

    @GetMapping(value = "/userPasswordMatch")
    public Map<String, String> userPasswordMatch(@RequestParam String email, @RequestParam String introduced_password) {
        Map<String, String> map = new HashMap<String, String>();
        if (personService.userPasswordMatch(email, introduced_password)) map.put("login_success", "true");
        else map.put("login_success", "false");
        return map;
    }

    @PostMapping
    public void registerNewPerson(@RequestBody Person person){
        personService.addNewPerson(person);
    }

    @DeleteMapping
    public void deletePerson(@RequestParam String email) {
        personService.deleteAditionalInfo(email);

    }
}
