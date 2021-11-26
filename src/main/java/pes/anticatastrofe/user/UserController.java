package pes.anticatastrofe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.aditionalInfo.AditionalInfoService;
import pes.anticatastrofe.person.PersonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;
    private final PersonService personService;
    private final AditionalInfoService aditionalInfoService;

    @Autowired
    public UserController(UserService userService, PersonService personService, AditionalInfoService aditionalInfoService) {
        this.userService = userService;
        this.personService = personService;
        this.aditionalInfoService = aditionalInfoService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        List<User> users = userService.getUsers();
        return users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        if (!userService.findByID(user.getEmail()).isPresent()) {
            personService.addNewPerson(user.getPerson());
            User u = userService.addNewUser(user);
            response.put("operation_success", "true");
            response.put("created_object_id", u.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "user already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> updateUserPosition(@RequestParam String email, @RequestParam float last_coordinate_x, @RequestParam float last_coordinate_y) {
        Map<String, String> response = new HashMap<>();
        if (userService.findByID(email).isPresent()) {
            User u = userService.updateUserPosition(email,last_coordinate_x,last_coordinate_y);
            response.put("operation_success", "true");
            response.put("modified_object_id", u.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "user not exists exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteUser(@RequestParam String email) {
        Map<String, String> response = new HashMap<>();
        if (userService.findByID(email).isPresent()) {
            personService.deletePerson(email);
            userService.deleteUser(email);
            if (aditionalInfoService.findByID(email).isPresent()) aditionalInfoService.deleteAditionalInfo(email);
            response.put("operation_success", "true");
            response.put("deleted_user_id", email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "user not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
