package pes.anticatastrofe.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/admin")

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
                .map(admin->new AdminDTO(admin))
                .collect(Collectors.toList());
        return adminsDTO;
    }

    @PostMapping
    public Map<String, String> registerNewAdmin(@RequestBody Admin admin){
        Admin a = adminService.addNewAdmin(admin);
        Map<String, String> map = new HashMap<String, String>();
        map.put("operation_success", "true");
        map.put("deleted_person_id",a.email);
        return map;
    }

    @DeleteMapping
    public Map<String,String> deleteAdmin(@RequestParam String email) {
        adminService.deleteAditionalInfo(email);
        Map<String, String> map = new HashMap<String, String>();
        map.put("operation_success", "true");
        map.put("deleted_person_id",email);
        return map;
    }
}
