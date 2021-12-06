package pes.anticatastrofe.message;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.person.PersonService;
import pes.anticatastrofe.tag.TagDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/message")
public class MessageController {
    @Autowired
    private final MessageService messageService;
    private final PersonService personService;

    @Autowired
    public MessageController(MessageService MessageService, PersonService personService) {
        this.messageService = MessageService;
        this.personService = personService;
    }

    @ApiResponse(description = "Success",responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageDTO.class))))
    @GetMapping
    public List<MessageDTO> getMessages() {
        List<Message> pins = messageService.getMessages();
        return pins.stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MessageDTO.class))),
            @ApiResponse(description = "Duplicated object", responseCode = "208", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "Failed dependency on object", responseCode = "424", content = @Content(schema = @Schema(hidden = true)))}
    )
    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewMessage(@RequestBody MessageDTOIn messageDTOIn) {
        Map<String, String> response = new HashMap<>();
        Optional<Message> message = messageService.findByID(messageDTOIn.getId());
        Optional<Person> recipient = personService.findByID(messageDTOIn.getRecipient_email());
        Optional<Person> sender = personService.findByID(messageDTOIn.getSender_email());
        if (message.isPresent()) throw new DuplicateKeyException("");
        if (!recipient.isPresent()) throw new DataIntegrityViolationException("");
        if (!sender.isPresent()) throw new DataIntegrityViolationException("");

        Person r = recipient.get();
        Person s = sender.get();
        Message m = new Message(messageDTOIn,s,r);
        m= messageService.addNewMessage(m);
        response.put("operation_success", "true");
        response.put("new_Message_id", String.valueOf(m.getId()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteMessage(@RequestParam int id) {
        Map<String, String> response = new HashMap<>();
        if (messageService.findByID(id).isPresent()) {
            messageService.deleteMessage(id);
            response.put("operation_success", "true");
            response.put("deleted_Message_id", String.valueOf(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new EmptyResultDataAccessException(1);
    }
}