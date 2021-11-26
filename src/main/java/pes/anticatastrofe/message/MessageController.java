package pes.anticatastrofe.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/message")
public class MessageController {
    @Autowired
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService MessageService) {
        this.messageService = MessageService;
    }

    @GetMapping
    public List<MessageDTO> getMessages() {
        List<Message> pins = messageService.getMessages();
        return pins.stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewMessage(@RequestBody Message Message) {
        Map<String, String> response = new HashMap<>();
        if (!messageService.findByID(Message.getId()).isPresent()) {
            Message m= messageService.addNewMessage(Message);
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
        if (messageService.findByID(id).isPresent()) {
            messageService.deleteMessage(id);
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