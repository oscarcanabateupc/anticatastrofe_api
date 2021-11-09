package pes.anticatastrofe.landmark;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.person.PersonRepository;
import pes.anticatastrofe.tag.Tag;
import pes.anticatastrofe.tag.TagRepository;

@DataJpaTest
class LandmarkRepositoryTest {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    LandmarkRepository landmarkRepository;

    @BeforeEach
    void setUp() {
        Person p = new Person("a", "a", 1, "a", "a", null);
        Tag t = new Tag("t");
        personRepository.save(p);
        tagRepository.save(t);
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
        tagRepository.deleteAll();
        landmarkRepository.deleteAll();
    }

    Landmark getTestLandmark() {
        Person p = new Person("a", "a", 1, "a", "a", null);
        Tag t = new Tag("t");
        return new Landmark(1, 1, 1, 1, p, t);
    }

    @Test
    void testInsert() {
        Landmark l = getTestLandmark();
        landmarkRepository.save(l);
        Boolean exist = landmarkRepository.existsById(1);
        assert exist;
    }

    @Test
    void testSelect() {
        Landmark l = getTestLandmark();
        landmarkRepository.save(l);
        Boolean exist_1 = landmarkRepository.existsById(1);
        Boolean exist_2 = landmarkRepository.existsById(2);
        assert exist_1;
        assert !exist_2;
    }

    @Test
    void testDelete() {
        Landmark l = getTestLandmark();
        landmarkRepository.save(l);
        landmarkRepository.deleteById(1);
        Boolean exist = landmarkRepository.existsById(1);
        assert !exist;
    }
}