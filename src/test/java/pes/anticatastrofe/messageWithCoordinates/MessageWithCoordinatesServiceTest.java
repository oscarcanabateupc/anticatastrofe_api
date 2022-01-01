package pes.anticatastrofe.messageWithCoordinates;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pes.anticatastrofe.landmark.Landmark;
import pes.anticatastrofe.message.Message;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.tag.Tag;

@ContextConfiguration(classes = {MessageWithCoordinatesService.class})
@ExtendWith(SpringExtension.class)
class MessageWithCoordinatesServiceTest {
    @MockBean
    private MessageWithCoordinatesRepository messageWithCoordinatesRepository;

    @Autowired
    private MessageWithCoordinatesService messageWithCoordinatesService;

    @Test
    void testGetMessages() {
        ArrayList<MessageWithCoordinates> messageWithCoordinatesList = new ArrayList<>();
        when(this.messageWithCoordinatesRepository.findAll()).thenReturn(messageWithCoordinatesList);
        List<MessageWithCoordinates> actualMessages = this.messageWithCoordinatesService.getMessages();
        assertSame(messageWithCoordinatesList, actualMessages);
        assertTrue(actualMessages.isEmpty());
        verify(this.messageWithCoordinatesRepository).findAll();
    }

    @Test
    void testGetMessageById() {
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

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        Person person2 = new Person();
        person2.setEmail("jane.doe@example.org");
        person2.setLandmark(new ArrayList<>());
        person2.setName("Name");
        person2.setPassword("iloveyou");
        person2.setPhone_num(10);
        person2.setToken("ABC123");

        Message message = new Message();
        message.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        message.setDate_sent(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        message.setId(1);
        message.setRecipient(person1);
        message.setSeen(true);
        message.setSender(person2);

        MessageWithCoordinates messageWithCoordinates = new MessageWithCoordinates();
        messageWithCoordinates.setId(1);
        messageWithCoordinates.setLandmark(landmark);
        messageWithCoordinates.setMessage(message);
        Optional<MessageWithCoordinates> ofResult = Optional.of(messageWithCoordinates);
        when(this.messageWithCoordinatesRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<MessageWithCoordinates> actualMessageById = this.messageWithCoordinatesService.getMessageById(1);
        assertSame(ofResult, actualMessageById);
        assertTrue(actualMessageById.isPresent());
        verify(this.messageWithCoordinatesRepository).findById((Integer) any());
    }

    @Test
    void testAddNewMessage() {
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

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        Person person2 = new Person();
        person2.setEmail("jane.doe@example.org");
        person2.setLandmark(new ArrayList<>());
        person2.setName("Name");
        person2.setPassword("iloveyou");
        person2.setPhone_num(10);
        person2.setToken("ABC123");

        Message message = new Message();
        message.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        message.setDate_sent(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        message.setId(1);
        message.setRecipient(person1);
        message.setSeen(true);
        message.setSender(person2);

        MessageWithCoordinates messageWithCoordinates = new MessageWithCoordinates();
        messageWithCoordinates.setId(1);
        messageWithCoordinates.setLandmark(landmark);
        messageWithCoordinates.setMessage(message);
        when(this.messageWithCoordinatesRepository.save((MessageWithCoordinates) any())).thenReturn(messageWithCoordinates);

        Person person3 = new Person();
        person3.setEmail("jane.doe@example.org");
        person3.setLandmark(new ArrayList<>());
        person3.setName("Name");
        person3.setPassword("iloveyou");
        person3.setPhone_num(10);
        person3.setToken("ABC123");

        Tag tag1 = new Tag();
        tag1.setColor(10.0f);
        tag1.setDescription("The characteristics of someone or something");
        tag1.setName("Name");

        Landmark landmark1 = new Landmark();
        landmark1.setCoordinate_x(10.0f);
        landmark1.setCoordinate_y(10.0f);
        landmark1.setCreator(person3);
        landmark1.setDescription("The characteristics of someone or something");
        landmark1.setId(1);
        landmark1.setRadius(10.0f);
        landmark1.setTag(tag1);
        landmark1.setTitle("Dr");
        landmark1.set_area(true);

        Person person4 = new Person();
        person4.setEmail("jane.doe@example.org");
        person4.setLandmark(new ArrayList<>());
        person4.setName("Name");
        person4.setPassword("iloveyou");
        person4.setPhone_num(10);
        person4.setToken("ABC123");

        Person person5 = new Person();
        person5.setEmail("jane.doe@example.org");
        person5.setLandmark(new ArrayList<>());
        person5.setName("Name");
        person5.setPassword("iloveyou");
        person5.setPhone_num(10);
        person5.setToken("ABC123");

        Message message1 = new Message();
        message1.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        message1.setDate_sent(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        message1.setId(1);
        message1.setRecipient(person4);
        message1.setSeen(true);
        message1.setSender(person5);

        MessageWithCoordinates messageWithCoordinates1 = new MessageWithCoordinates();
        messageWithCoordinates1.setId(1);
        messageWithCoordinates1.setLandmark(landmark1);
        messageWithCoordinates1.setMessage(message1);
        assertSame(messageWithCoordinates, this.messageWithCoordinatesService.addNewMessage(messageWithCoordinates1));
        verify(this.messageWithCoordinatesRepository).save((MessageWithCoordinates) any());
    }

    @Test
    void testDeleteMessage() {
        doNothing().when(this.messageWithCoordinatesRepository).deleteById((Integer) any());
        this.messageWithCoordinatesService.deleteMessage(1);
        verify(this.messageWithCoordinatesRepository).deleteById((Integer) any());
        assertTrue(this.messageWithCoordinatesService.getMessages().isEmpty());
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

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        Person person2 = new Person();
        person2.setEmail("jane.doe@example.org");
        person2.setLandmark(new ArrayList<>());
        person2.setName("Name");
        person2.setPassword("iloveyou");
        person2.setPhone_num(10);
        person2.setToken("ABC123");

        Message message = new Message();
        message.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        message.setDate_sent(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        message.setId(1);
        message.setRecipient(person1);
        message.setSeen(true);
        message.setSender(person2);

        MessageWithCoordinates messageWithCoordinates = new MessageWithCoordinates();
        messageWithCoordinates.setId(1);
        messageWithCoordinates.setLandmark(landmark);
        messageWithCoordinates.setMessage(message);
        Optional<MessageWithCoordinates> ofResult = Optional.of(messageWithCoordinates);
        when(this.messageWithCoordinatesRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<MessageWithCoordinates> actualFindByIDResult = this.messageWithCoordinatesService.findByID(1);
        assertSame(ofResult, actualFindByIDResult);
        assertTrue(actualFindByIDResult.isPresent());
        verify(this.messageWithCoordinatesRepository).findById((Integer) any());
    }
}

