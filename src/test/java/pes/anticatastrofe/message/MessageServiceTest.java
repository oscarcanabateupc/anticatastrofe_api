package pes.anticatastrofe.message;

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
import pes.anticatastrofe.person.Person;

@ContextConfiguration(classes = {MessageService.class})
@ExtendWith(SpringExtension.class)
class MessageServiceTest {
    @MockBean
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @Test
    void testGetMessages() {
        ArrayList<Message> messageList = new ArrayList<>();
        when(this.messageRepository.findAll()).thenReturn(messageList);
        List<Message> actualMessages = this.messageService.getMessages();
        assertSame(messageList, actualMessages);
        assertTrue(actualMessages.isEmpty());
        verify(this.messageRepository).findAll();
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

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        Message message = new Message();
        message.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        message.setDate_sent(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        message.setId(1);
        message.setRecipient(person);
        message.setSeen(true);
        message.setSender(person1);
        Optional<Message> ofResult = Optional.of(message);
        when(this.messageRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Message> actualMessageById = this.messageService.getMessageById(1);
        assertSame(ofResult, actualMessageById);
        assertTrue(actualMessageById.isPresent());
        verify(this.messageRepository).findById((Integer) any());
        assertTrue(this.messageService.getMessages().isEmpty());
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

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        Message message = new Message();
        message.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        message.setDate_sent(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        message.setId(1);
        message.setRecipient(person);
        message.setSeen(true);
        message.setSender(person1);
        when(this.messageRepository.save((Message) any())).thenReturn(message);

        Person person2 = new Person();
        person2.setEmail("jane.doe@example.org");
        person2.setLandmark(new ArrayList<>());
        person2.setName("Name");
        person2.setPassword("iloveyou");
        person2.setPhone_num(10);
        person2.setToken("ABC123");

        Person person3 = new Person();
        person3.setEmail("jane.doe@example.org");
        person3.setLandmark(new ArrayList<>());
        person3.setName("Name");
        person3.setPassword("iloveyou");
        person3.setPhone_num(10);
        person3.setToken("ABC123");

        Message message1 = new Message();
        message1.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        message1.setDate_sent(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        message1.setId(1);
        message1.setRecipient(person2);
        message1.setSeen(true);
        message1.setSender(person3);
        assertSame(message, this.messageService.addNewMessage(message1));
        verify(this.messageRepository).save((Message) any());
        assertTrue(this.messageService.getMessages().isEmpty());
    }

    @Test
    void testDeleteMessage() {
        doNothing().when(this.messageRepository).deleteById((Integer) any());
        this.messageService.deleteMessage(1);
        verify(this.messageRepository).deleteById((Integer) any());
        assertTrue(this.messageService.getMessages().isEmpty());
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

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        Message message = new Message();
        message.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        message.setDate_sent(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        message.setId(1);
        message.setRecipient(person);
        message.setSeen(true);
        message.setSender(person1);
        Optional<Message> ofResult = Optional.of(message);
        when(this.messageRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Message> actualFindByIDResult = this.messageService.findByID(1);
        assertSame(ofResult, actualFindByIDResult);
        assertTrue(actualFindByIDResult.isPresent());
        verify(this.messageRepository).findById((Integer) any());
        assertTrue(this.messageService.getMessages().isEmpty());
    }
}

