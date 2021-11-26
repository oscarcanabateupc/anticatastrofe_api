package pes.anticatastrofe.messageWithCoordinates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/messageWithCoordinates")
public class MessageWithCoordinatesController {
    @Autowired
    private final MessageWithCoordinatesService messageWithCoordinatesService;

    @Autowired
    public MessageWithCoordinatesController(MessageWithCoordinatesService messageWithCoordinatesService) {
        this.messageWithCoordinatesService = messageWithCoordinatesService;
    }

    @GetMapping
    public List<MessageWithCoordinatesDTO> getMessages() {
        List<MessageWithCoordinates> pins = messageWithCoordinatesService.getMessages();
        return pins.stream()
                .map(MessageWithCoordinatesDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewMessage(@RequestBody MessageWithCoordinates Message) {
        Map<String, String> response = new HashMap<>();
        if (!messageWithCoordinatesService.findByID(Message.getId()).isPresent()) {
            MessageWithCoordinates m= messageWithCoordinatesService.addNewMessage(Message);
            response.put("operation_success", "true");
            response.put("new_Message_id", String.valueOf(m.getId()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Message already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteMessage(@RequestParam int id) {
        Map<String, String> response = new HashMap<>();
        if (messageWithCoordinatesService.findByID(id).isPresent()) {
            messageWithCoordinatesService.deleteMessage(id);
            response.put("operation_success", "true");
            response.put("deleted_Message_id", String.valueOf(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Message not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}