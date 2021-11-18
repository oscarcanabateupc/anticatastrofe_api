package pes.anticatastrofe.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Person addNewPerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(String email) {
        personRepository.deleteById(email);
    }

    public boolean userPasswordMatch(String email, String introduced_password) {
        return introduced_password.equals(personRepository.findById(email).get().getPassword());
    }

    public Optional<Person> findByID(String email) {
        return personRepository.findById(email);
    }

    public String resetToken(String email, String introduced_token) {
        Person p = personRepository.findById(email).get();
        p.setToken(introduced_token);
        p = personRepository.save(p);
        return p.getToken();
    }

    public String getToken(String email) {
        Person p = personRepository.findById(email).get();
        return p.getToken();
    }
}
