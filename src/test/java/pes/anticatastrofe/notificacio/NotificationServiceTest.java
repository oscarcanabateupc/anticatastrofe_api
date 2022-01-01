package pes.anticatastrofe.notificacio;

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
import pes.anticatastrofe.landmark.Landmark;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.tag.Tag;

@ContextConfiguration(classes = {NotificationService.class})
@ExtendWith(SpringExtension.class)
class NotificationServiceTest {
    @MockBean
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    @Test
    void testGetNotification() {
        ArrayList<Notification> notificationList = new ArrayList<>();
        when(this.notificationRepository.findAll()).thenReturn(notificationList);
        List<Notification> actualNotification = this.notificationService.getNotification();
        assertSame(notificationList, actualNotification);
        assertTrue(actualNotification.isEmpty());
        verify(this.notificationRepository).findAll();
    }

    @Test
    void testAddNewNotification() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");

        Tag tag = new Tag();
        tag.setColor(10.0f);
        tag.setDescription("The characteristics of someone or something");
        tag.setName("Name");

        Landmark landmark = new Landmark();
        landmark.setCoordinate_x(10.0f);
        landmark.setCoordinate_y(10.0f);
        landmark.setCreator(person);
        landmark.setDescription("The characteristics of someone or something");
        landmark.setId(1);
        landmark.setRadius(10.0f);
        landmark.setTag(tag);
        landmark.setTitle("Dr");
        landmark.set_area(true);

        Tag tag1 = new Tag();
        tag1.setColor(10.0f);
        tag1.setDescription("The characteristics of someone or something");
        tag1.setName("Name");

        Notification notification = new Notification();
        notification.setDescription("The characteristics of someone or something");
        notification.setId(1);
        notification.setLandmark(landmark);
        notification.setTag(tag1);
        when(this.notificationRepository.save((Notification) any())).thenReturn(notification);

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        Tag tag2 = new Tag();
        tag2.setColor(10.0f);
        tag2.setDescription("The characteristics of someone or something");
        tag2.setName("Name");

        Landmark landmark1 = new Landmark();
        landmark1.setCoordinate_x(10.0f);
        landmark1.setCoordinate_y(10.0f);
        landmark1.setCreator(person1);
        landmark1.setDescription("The characteristics of someone or something");
        landmark1.setId(1);
        landmark1.setRadius(10.0f);
        landmark1.setTag(tag2);
        landmark1.setTitle("Dr");
        landmark1.set_area(true);

        Tag tag3 = new Tag();
        tag3.setColor(10.0f);
        tag3.setDescription("The characteristics of someone or something");
        tag3.setName("Name");

        Notification notification1 = new Notification();
        notification1.setDescription("The characteristics of someone or something");
        notification1.setId(1);
        notification1.setLandmark(landmark1);
        notification1.setTag(tag3);
        assertSame(notification, this.notificationService.addNewNotification(notification1));
        verify(this.notificationRepository).save((Notification) any());
    }

    @Test
    void testDeleteNotification() {
        doNothing().when(this.notificationRepository).deleteById((Integer) any());
        this.notificationService.deleteNotification(1);
        verify(this.notificationRepository).deleteById((Integer) any());
        assertTrue(this.notificationService.getNotification().isEmpty());
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

        Tag tag = new Tag();
        tag.setColor(10.0f);
        tag.setDescription("The characteristics of someone or something");
        tag.setName("Name");

        Landmark landmark = new Landmark();
        landmark.setCoordinate_x(10.0f);
        landmark.setCoordinate_y(10.0f);
        landmark.setCreator(person);
        landmark.setDescription("The characteristics of someone or something");
        landmark.setId(1);
        landmark.setRadius(10.0f);
        landmark.setTag(tag);
        landmark.setTitle("Dr");
        landmark.set_area(true);

        Tag tag1 = new Tag();
        tag1.setColor(10.0f);
        tag1.setDescription("The characteristics of someone or something");
        tag1.setName("Name");

        Notification notification = new Notification();
        notification.setDescription("The characteristics of someone or something");
        notification.setId(1);
        notification.setLandmark(landmark);
        notification.setTag(tag1);
        Optional<Notification> ofResult = Optional.of(notification);
        when(this.notificationRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Notification> actualFindByIDResult = this.notificationService.findByID(1);
        assertSame(ofResult, actualFindByIDResult);
        assertTrue(actualFindByIDResult.isPresent());
        verify(this.notificationRepository).findById((Integer) any());
        assertTrue(this.notificationService.getNotification().isEmpty());
    }
}

