package pes.anticatastrofe.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAdmins() { return adminRepository.findAll();
    }

    public Admin addNewAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(String email) {
        adminRepository.deleteById(email);
    }

    public Optional<Admin> findByID(String email) {
        return adminRepository.findById(email);
    }
}
