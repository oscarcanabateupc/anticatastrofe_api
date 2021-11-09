package pes.anticatastrofe.admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.person.PersonRepository;

@DataJpaTest
class AdminRepositoryTest {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        Person p = new Person("a", "a", 1, "a", "a", null);
        personRepository.save(p);
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
        adminRepository.deleteAll();
    }

    Admin getTestAdmin() {
        Person p = new Person("a", "a", 1, "a", "a", null);
        return new Admin("a", "a", p);
    }

    @Test
    void testInsert() {
        Admin a = getTestAdmin();
        adminRepository.save(a);
        Boolean exist = adminRepository.existsById("a");
        assert exist;
    }

    @Test
    void testSelect() {
        Admin a = getTestAdmin();
        adminRepository.save(a);
        Boolean exist_1 = adminRepository.existsById("a");
        Boolean exist_2 = adminRepository.existsById("b");
        assert exist_1;
        assert !exist_2;
    }

    @Test
    void testDelete() {
        Admin a = getTestAdmin();
        adminRepository.save(a);
        adminRepository.deleteById("a");
        Boolean exist = adminRepository.existsById("a");
        assert !exist;
    }
}