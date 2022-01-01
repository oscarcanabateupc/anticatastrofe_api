package pes.anticatastrofe.user;

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
import pes.anticatastrofe.person.Person;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testGetUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(userList);
        List<User> actualUsers = this.userService.getUsers();
        assertSame(userList, actualUsers);
        assertTrue(actualUsers.isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    void testAddNewUser() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setLast_coordinate_x(10.0f);
        user.setLast_coordinate_y(10.0f);
        user.setPerson(person);
        when(this.userRepository.save((User) any())).thenReturn(user);

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setLast_coordinate_x(10.0f);
        user1.setLast_coordinate_y(10.0f);
        user1.setPerson(person1);
        assertSame(user, this.userService.addNewUser(user1));
        verify(this.userRepository).save((User) any());
        assertTrue(this.userService.getUsers().isEmpty());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(this.userRepository).deleteById((String) any());
        this.userService.deleteUser("jane.doe@example.org");
        verify(this.userRepository).deleteById((String) any());
        assertTrue(this.userService.getUsers().isEmpty());
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

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setLast_coordinate_x(10.0f);
        user.setLast_coordinate_y(10.0f);
        user.setPerson(person);
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((String) any())).thenReturn(ofResult);
        Optional<User> actualFindByIDResult = this.userService.findByID("jane.doe@example.org");
        assertSame(ofResult, actualFindByIDResult);
        assertTrue(actualFindByIDResult.isPresent());
        verify(this.userRepository).findById((String) any());
        assertTrue(this.userService.getUsers().isEmpty());
    }

    @Test
    void testUpdateUserPosition() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setLast_coordinate_x(10.0f);
        user.setLast_coordinate_y(10.0f);
        user.setPerson(person);
        Optional<User> ofResult = Optional.of(user);

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setLast_coordinate_x(10.0f);
        user1.setLast_coordinate_y(10.0f);
        user1.setPerson(person1);
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((String) any())).thenReturn(ofResult);
        assertSame(user1, this.userService.updateUserPosition("jane.doe@example.org", 10.0f, 10.0f));
        verify(this.userRepository).findById((String) any());
        verify(this.userRepository).save((User) any());
        assertTrue(this.userService.getUsers().isEmpty());
    }
}

