package pes.anticatastrofe.aditionalInfo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.person.PersonRepository;

@DataJpaTest
class AditionalInfoRepositoryTest {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AditionalInfoRepository aditionalInfoRepository;

    @BeforeEach
    void setUp() {
        Person p = new Person("a", "a", 1, "a", "a", null);
        personRepository.save(p);
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
        aditionalInfoRepository.deleteAll();
    }

    AditionalInfo getTestAditionalInfo() {
        Person p = new Person("a", "a", 1, "a", "a", null);
        return new AditionalInfo("a", "a", "a", "a", "a", "a", "a", p);
    }

    @Test
    void testInsert() {
        AditionalInfo a = getTestAditionalInfo();
        aditionalInfoRepository.save(a);
        Boolean exist = aditionalInfoRepository.existsById("a");
        assert exist;
    }

    @Test
    void testSelect() {
        AditionalInfo a = getTestAditionalInfo();
        aditionalInfoRepository.save(a);
        Boolean exist_1 = aditionalInfoRepository.existsById("a");
        Boolean exist_2 = aditionalInfoRepository.existsById("b");
        assert exist_1;
        assert !exist_2;
    }

    @Test
    void testDelete() {
        AditionalInfo a = getTestAditionalInfo();
        aditionalInfoRepository.save(a);
        aditionalInfoRepository.deleteById("a");
        Boolean exist = aditionalInfoRepository.existsById("a");
        assert !exist;
    }
}