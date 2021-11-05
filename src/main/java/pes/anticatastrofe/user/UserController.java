package pes.anticatastrofe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/user")
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
                                        .map(user->new UserDTO(user))
                                        .collect(Collectors.toList());
        return usersDTO;
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam String email) {
        userService.deleteUser(email);

    }
}
