package pes.anticatastrofe.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PersonService.class})
@ExtendWith(SpringExtension.class)
class PersonServiceTest {
    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Test
    void testGetPersons() {
        ArrayList<Person> personList = new ArrayList<>();
        when(this.personRepository.findAll()).thenReturn(personList);
        List<Person> actualPersons = this.personService.getPersons();
        assertSame(personList, actualPersons);
        assertTrue(actualPersons.isEmpty());
        verify(this.personRepository).findAll();
    }

    @Test
    void testAddNewPerson() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");
        when(this.personRepository.save((Person) any())).thenReturn(person);

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");
        assertSame(person, this.personService.addNewPerson(person1));
        verify(this.personRepository).save((Person) any());
        assertTrue(this.personService.getPersons().isEmpty());
    }

    @Test
    void testDeletePerson() {
        doNothing().when(this.personRepository).deleteById((String) any());
        this.personService.deletePerson("jane.doe@example.org");
        verify(this.personRepository).deleteById((String) any());
        assertTrue(this.personService.getPersons().isEmpty());
    }

    @Test
    void testUserPasswordMatch() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");
        Optional<Person> ofResult = Optional.of(person);
        when(this.personRepository.findById((String) any())).thenReturn(ofResult);
        assertTrue(this.personService.userPasswordMatch("jane.doe@example.org", "iloveyou"));
        verify(this.personRepository).findById((String) any());
        assertTrue(this.personService.getPersons().isEmpty());
    }

    @Test
    void testUserPasswordMatch2() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("Password");
        person.setPhone_num(10);
        person.setToken("ABC123");
        Optional<Person> ofResult = Optional.of(person);
        when(this.personRepository.findById((String) any())).thenReturn(ofResult);
        assertFalse(this.personService.userPasswordMatch("jane.doe@example.org", "iloveyou"));
        verify(this.personRepository).findById((String) any());
        assertTrue(this.personService.getPersons().isEmpty());
    }

    @Test
    void testFindByID() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");
        Optional<Person> ofResult = Optional.of(person);
        when(this.personRepository.findById((String) any())).thenReturn(ofResult);
        Optional<Person> actualFindByIDResult = this.personService.findByID("jane.doe@example.org");
        assertSame(ofResult, actualFindByIDResult);
        assertTrue(actualFindByIDResult.isPresent());
        verify(this.personRepository).findById((String) any());
        assertTrue(this.personService.getPersons().isEmpty());
    }

    @Test
    void testResetToken() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");
        Optional<Person> ofResult = Optional.of(person);

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");
        when(this.personRepository.save((Person) any())).thenReturn(person1);
        when(this.personRepository.findById((String) any())).thenReturn(ofResult);
        assertEquals("ABC123", this.personService.resetToken("jane.doe@example.org", "ABC123"));
        verify(this.personRepository).findById((String) any());
        verify(this.personRepository).save((Person) any());
        assertTrue(this.personService.getPersons().isEmpty());
    }

    @Test
    void testGetToken() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");
        Optional<Person> ofResult = Optional.of(person);
        when(this.personRepository.findById((String) any())).thenReturn(ofResult);
        assertEquals("ABC123", this.personService.getToken("jane.doe@example.org"));
        verify(this.personRepository).findById((String) any());
        assertTrue(this.personService.getPersons().isEmpty());
    }
}

