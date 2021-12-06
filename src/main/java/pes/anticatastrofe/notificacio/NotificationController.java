package pes.anticatastrofe.notificacio;


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
import pes.anticatastrofe.tag.Tag;
import pes.anticatastrofe.tag.TagDTO;
import pes.anticatastrofe.tag.TagService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/notification")

public class NotificationController {
    private final NotificationService notificationService;
    private final LandmarkService landmarkService;
    private final TagService tagService;

    @Autowired
    public NotificationController(NotificationService notificationService,TagService tagService, LandmarkService landmarkService) {
        this.notificationService = notificationService;
        this.landmarkService = landmarkService;
        this.tagService = tagService;
    }

    @ApiResponse(description = "Success",responseCode = "200",content = @Content(array = @ArraySchema(schema = @Schema(implementation = NotificationDTO.class))))
    @GetMapping
    public List<NotificationDTO> getNotifications() {
        List<Notification> notifications = notificationService.getNotification();
        return notifications.stream()
                .map(NotificationDTO::new)
                .collect(Collectors.toList());
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = NotificationDTO.class))),
            @ApiResponse(description = "Duplicated object", responseCode = "208", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "Failed dependency on object", responseCode = "424", content = @Content(schema = @Schema(hidden = true)))}
    )
    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewNotification(@RequestBody NotificationDTOIn notificationDTOIn) {
        Map<String, String> response = new HashMap<>();
        Optional<Notification> notification = notificationService.findByID(notificationDTOIn.getId());
        Optional<Landmark> landmark = landmarkService.findByID(notificationDTOIn.getLandmark_id());
        Optional<Tag> tag = tagService.findByID(notificationDTOIn.getTag());
        if (notification.isPresent()) throw new DuplicateKeyException("");
        if (!landmark.isPresent()) throw new DataIntegrityViolationException("");
        if (!tag.isPresent()) throw new DataIntegrityViolationException("");

        Tag t = tag.get();
        Landmark l = landmark.get();
        Notification n = new Notification(notificationDTOIn,t,l);
        n = notificationService.addNewNotification(n);
        response.put("operation_success", "true");
        response.put("new_object_id", String.valueOf(n.getId()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(description = "Success",responseCode = "200"),
            @ApiResponse(description = "Object not exists", responseCode = "404", content = @Content(schema = @Schema(hidden = true)))}
    )
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteNotification(@RequestParam int id) {
        Map<String, String> response = new HashMap<>();
        if (notificationService.findByID(id).isPresent()) {
            notificationService.deleteNotification(id);
            response.put("operation_success", "true");
            response.put("deleted_object_id", String.valueOf(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else throw new EmptyResultDataAccessException(1);
    }
}
