package pes.anticatastrofe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Map<String, String> registerNewUser(@RequestBody User user){
        User u = userService.addNewUser(user);
        Map<String, String> map = new HashMap<String, String>();
        map.put("operation_success", "true");
        map.put("new_user_id",u.email);
        return map;
    }

    @DeleteMapping
    public Map<String,String> deleteUser(@RequestParam String email) {
        userService.deleteUser(email);
        Map<String, String> map = new HashMap<String, String>();
        map.put("operation_success", "true");
        map.put("deleted_user_id",email);
        return map;
    }
}
