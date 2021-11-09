package pes.anticatastrofe.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @Test
    void testInsert() {
        Person p = new Person("a", "a", 1, "a", "a", null);
        personRepository.save(p);
        Boolean exist = personRepository.existsById("a");
        assert exist;
    }

    @Test
    void testSelect() {
        Person p1 = new Person("a", "a", 1, "a", "a", null);

        personRepository.save(p1);
        Boolean exist_1 = personRepository.existsById("a");
        Boolean exist_2 = personRepository.existsById("b");
        assert exist_1;
        assert !exist_2;
    }

    @Test
    void testDelete() {
        Person p1 = new Person("a", "a", 1, "a", "a", null);

        personRepository.save(p1);
        personRepository.deleteById("a");
        Boolean exist = personRepository.existsById("a");
        assert !exist;
    }
}