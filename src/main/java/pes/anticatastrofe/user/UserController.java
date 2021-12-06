package pes.anticatastrofe.user;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.aditionalInfo.AditionalInfoService;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.person.PersonService;
import pes.anticatastrofe.tag.TagDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @ApiResponse(description = "Success",responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    @GetMapping
    public List<UserDTO> getUsers() {
        List<User> users = userService.getUsers();
        return users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(description = "Duplicated object", responseCode = "208", content = @Content(schema = @Schema(hidden = true)))}
    )
    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewUser(@RequestBody UserDTOIn userDTOIn) {
        Map<String, String> response = new HashMap<>();
        Optional<Person> person = personService.findByID(userDTOIn.getEmail());
        if (!person.isPresent()) {
            Person p = new Person(userDTOIn);
            personService.addNewPerson(p);
            User u = new User(userDTOIn,p);
            u = userService.addNewUser(u);
            response.put("operation_success", "true");
            response.put("created_object_id", u.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new DuplicateKeyException("");
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> updateUserPosition(@RequestParam String email, @RequestParam float last_coordinate_x, @RequestParam float last_coordinate_y) {
        Map<String, String> response = new HashMap<>();
        if (userService.findByID(email).isPresent()) {
            User u = userService.updateUserPosition(email,last_coordinate_x,last_coordinate_y);
            response.put("operation_success", "true");
            response.put("modified_object_id", u.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new DuplicateKeyException("");
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
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
        } else throw new EmptyResultDataAccessException(1);
    }
}
