package pes.anticatastrofe.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/admin")

public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<AdminDTO> getAdmins() {
        List<Admin> users = adminService.getAdmins();
        List<AdminDTO> adminsDTO = users.stream()
                .map(admin -> new AdminDTO(admin))
                .collect(Collectors.toList());
        return adminsDTO;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewAdmin(@RequestBody Admin admin) {
        Map<String, String> response = new HashMap<String, String>();
        if (!adminService.findByID(admin.email).isPresent()) {
            Admin a = adminService.addNewAdmin(admin);
            response.put("operation_success", "true");
            response.put("deleted_object_id", a.email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "admin already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteAdmin(@RequestParam String email) {
        Map<String, String> response = new HashMap<String, String>();
        if (adminService.findByID(email).isPresent()) {
            adminService.deleteAditionalInfo(email);
            response.put("operation_success", "true");
            response.put("deleted_person_id", email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "admin not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
