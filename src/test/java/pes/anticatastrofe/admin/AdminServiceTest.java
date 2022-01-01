package pes.anticatastrofe.admin;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pes.anticatastrofe.person.Person;

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
class AdminServiceTest {
    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @Test
    void testGetAdmins() {
        ArrayList<Admin> adminList = new ArrayList<>();
        when(this.adminRepository.findAll()).thenReturn(adminList);
        List<Admin> actualAdmins = this.adminService.getAdmins();
        assertSame(adminList, actualAdmins);
        assertTrue(actualAdmins.isEmpty());
        verify(this.adminRepository).findAll();
    }

    @Test
    void testAddNewAdmin() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");

        Admin admin = new Admin();
        admin.setEmail("jane.doe@example.org");
        admin.setPerson(person);
        admin.setRegionality("us-east-2");
        when(this.adminRepository.save((Admin) any())).thenReturn(admin);

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        Admin admin1 = new Admin();
        admin1.setEmail("jane.doe@example.org");
        admin1.setPerson(person1);
        admin1.setRegionality("us-east-2");
        assertSame(admin, this.adminService.addNewAdmin(admin1));
        verify(this.adminRepository).save((Admin) any());
        assertTrue(this.adminService.getAdmins().isEmpty());
    }

    @Test
    void testDeleteAdmin() {
        doNothing().when(this.adminRepository).deleteById((String) any());
        this.adminService.deleteAdmin("jane.doe@example.org");
        verify(this.adminRepository).deleteById((String) any());
        assertTrue(this.adminService.getAdmins().isEmpty());
    }

    @Test
    void testFindByID() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");

        Admin admin = new Admin();
        admin.setEmail("jane.doe@example.org");
        admin.setPerson(person);
        admin.setRegionality("us-east-2");
        Optional<Admin> ofResult = Optional.of(admin);
        when(this.adminRepository.findById((String) any())).thenReturn(ofResult);
        Optional<Admin> actualFindByIDResult = this.adminService.findByID("jane.doe@example.org");
        assertSame(ofResult, actualFindByIDResult);
        assertTrue(actualFindByIDResult.isPresent());
        verify(this.adminRepository).findById((String) any());
        assertTrue(this.adminService.getAdmins().isEmpty());
    }
}

