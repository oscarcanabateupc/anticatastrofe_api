package pes.anticatastrofe.messageWithCoordinates;

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
import pes.anticatastrofe.tag.TagDTO;

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
            @ApiResponse(description = "Duplicated object", responseCode = "208", content = @Content(schema = @Schema(hidden = true)))}
    )
    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewMessage(@RequestBody MessageWithCoordinates Message) {
        Map<String, String> response = new HashMap<>();
        if (!messageWithCoordinatesService.findByID(Message.getId()).isPresent()) {
            MessageWithCoordinates m= messageWithCoordinatesService.addNewMessage(Message);
            response.put("operation_success", "true");
            response.put("new_Message_id", String.valueOf(m.getId()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new DuplicateKeyException("");
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