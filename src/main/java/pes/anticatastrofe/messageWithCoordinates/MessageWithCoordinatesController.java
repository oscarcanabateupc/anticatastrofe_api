package pes.anticatastrofe.messageWithCoordinates;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.landmark.Landmark;
import pes.anticatastrofe.landmark.LandmarkService;
import pes.anticatastrofe.message.Message;
import pes.anticatastrofe.message.MessageService;
import pes.anticatastrofe.person.Person;
import pes.anticatastrofe.person.PersonService;
import pes.anticatastrofe.tag.TagDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/messageWithCoordinates")
public class MessageWithCoordinatesController {
    @Autowired
    private final MessageWithCoordinatesService messageWithCoordinatesService;
    private final MessageService messageService;
    private final LandmarkService landmarkService;
    private final PersonService personService;

    @Autowired
    public MessageWithCoordinatesController(MessageWithCoordinatesService messageWithCoordinatesService,LandmarkService landmarkService, MessageService messageService,PersonService personService) {
        this.messageWithCoordinatesService = messageWithCoordinatesService;
        this.messageService = messageService;
        this.landmarkService = landmarkService;
        this.personService = personService;
    }

    @ApiResponse(description = "Success",responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = MessageWithCoordinatesDTO.class))))
    @GetMapping
    public List<MessageWithCoordinatesDTO> getMessages() {
        List<MessageWithCoordinates> pins = messageWithCoordinatesService.getMessages();
        return pins.stream()
                .map(MessageWithCoordinatesDTO::new)
                .collect(Collectors.toList());
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MessageWithCoordinatesDTO.class))),
            @ApiResponse(description = "Duplicated object", responseCode = "208", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "Failed dependency on object", responseCode = "424", content = @Content(schema = @Schema(hidden = true)))}
    )
    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewMessage(@RequestBody MessageWithCoordinatesDTOIn messageWithCoordinatesDTOIn) {
        Map<String, String> response = new HashMap<>();
        Optional<MessageWithCoordinates> messageWithCoordinates = messageWithCoordinatesService.findByID(messageWithCoordinatesDTOIn.getId());
        Optional<Message> message = messageService.findByID(messageWithCoordinatesDTOIn.getId());
        Optional<Landmark> landmark = landmarkService.findByID(messageWithCoordinatesDTOIn.getLandmark_id());
        Optional<Person> recipient = personService.findByID(messageWithCoordinatesDTOIn.getRecipient_email());
        Optional<Person> sender = personService.findByID(messageWithCoordinatesDTOIn.getSender_email());
        if (messageWithCoordinates.isPresent()) throw new DuplicateKeyException("");
        if (message.isPresent()) throw new DuplicateKeyException("");
        if (!landmark.isPresent()) throw new DataIntegrityViolationException("");
        if (!recipient.isPresent()) throw new DataIntegrityViolationException("");
        if (!sender.isPresent()) throw new DataIntegrityViolationException("");

        Landmark l = landmark.get();
        Person r = recipient.get();
        Person s = sender.get();
        Message m = new Message(messageWithCoordinatesDTOIn,s,r);
        m = messageService.addNewMessage(m);
        MessageWithCoordinates mwc = new MessageWithCoordinates(messageWithCoordinatesDTOIn,m,l);
        mwc = messageWithCoordinatesService.addNewMessage(mwc);
        response.put("operation_success", "true");
        response.put("new_Message_id", String.valueOf(mwc.getId()));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteMessage(@RequestParam int id) {
        Map<String, String> response = new HashMap<>();
        if (messageWithCoordinatesService.findByID(id).isPresent()) {
            messageWithCoordinatesService.deleteMessage(id);
            response.put("operation_success", "true");
            response.put("deleted_Message_id", String.valueOf(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new EmptyResultDataAccessException(1);
    }
}