package pes.anticatastrofe.notificacio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/notification")

public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationDTO> getPersons() {
        List<Notification> notifications = notificationService.getNotification();
        return notifications.stream()
                .map(NotificationDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewNotification(@RequestBody Notification notification) {
        Map<String, String> response = new HashMap<>();
        if (!notificationService.findByID(notification.getId()).isPresent()) {
            Notification n = notificationService.addNewNotification(notification);
            response.put("operation_success", "true");
            response.put("new_object_id", String.valueOf(n.getId()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "person already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deletePerson(@RequestParam int id) {
        Map<String, String> response = new HashMap<>();
        if (notificationService.findByID(id).isPresent()) {
            notificationService.deleteNotification(id);
            response.put("operation_success", "true");
            response.put("deleted_object_id", String.valueOf(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "person not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
