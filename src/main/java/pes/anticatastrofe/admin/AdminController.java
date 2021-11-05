package pes.anticatastrofe.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.user.User;
import pes.anticatastrofe.user.UserDTO;

import java.util.List;
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
    public void registerNewAdmin(@RequestBody Admin admin){
        adminService.addNewAdmin(admin);
    }

    @DeleteMapping
    public void deleteAdmin(@RequestParam String email) {
        adminService.deleteAditionalInfo(email);

    }
}
