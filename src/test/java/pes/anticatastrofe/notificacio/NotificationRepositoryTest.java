package pes.anticatastrofe.notificacio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pes.anticatastrofe.landmark.Landmark;
import pes.anticatastrofe.landmark.LandmarkRepository;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.person.PersonRepository;
import pes.anticatastrofe.tag.Tag;
import pes.anticatastrofe.tag.TagRepository;

@DataJpaTest
class NotificationRepositoryTest {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    TagRepository tagRepository;
    @Autowired
    LandmarkRepository landmarkRepository;

    @BeforeEach
    void setUp() {
        Person p = new Person("a", "a", 1, "a", "a", null);
        Tag t = new Tag("t","a");
        Landmark l = new Landmark(1, 1, 1,"a", p, t);
        personRepository.save(p);
        tagRepository.save(t);
        landmarkRepository.save(l);
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
        tagRepository.deleteAll();
        landmarkRepository.deleteAll();
        notificationRepository.deleteAll();
    }

    Notification getTestNotification() {
        Person p = new Person("a", "a", 1, "a", "a", null);
        Tag t = new Tag("t","a");
        Landmark l = new Landmark(1, 1, 1,"a", p, t);
        return new Notification(1,"a", t, l);
    }

    @Test
    void testInsert() {
        Notification n = getTestNotification();
        notificationRepository.save(n);
        Boolean exist = notificationRepository.existsById(1);
        assert exist;
    }

    @Test
    void testSelect() {
        Notification n = getTestNotification();
        notificationRepository.save(n);
        Boolean exist_1 = notificationRepository.existsById(1);
        Boolean exist_2 = notificationRepository.existsById(2);
        assert exist_1;
        assert !exist_2;
    }

    @Test
    void testDelete() {
        Notification n = getTestNotification();
        notificationRepository.save(n);
        notificationRepository.deleteById(1);
        Boolean exist = notificationRepository.existsById(1);
        assert !exist;
    }
}