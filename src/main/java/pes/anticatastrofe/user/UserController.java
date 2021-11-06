package pes.anticatastrofe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        List<User> users = userService.getUsers();
        List<UserDTO> usersDTO = users.stream()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
        return usersDTO;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<String, String>();
        if (!userService.findByID(user.email).isPresent()) {
            User u = userService.addNewUser(user);
            response.put("operation_success", "true");
            response.put("deleted_object_id", u.email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "user already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteUser(@RequestParam String email) {
        Map<String, String> response = new HashMap<String, String>();
        if (userService.findByID(email).isPresent()) {
            userService.deleteUser(email);
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
