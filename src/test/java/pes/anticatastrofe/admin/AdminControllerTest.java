package pes.anticatastrofe.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pes.anticatastrofe.aditionalInfo.AditionalInfoService;
import pes.anticatastrofe.person.PersonService;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @MockBean
    private AditionalInfoService aditionalInfoService;

    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

    @MockBean
    private PersonService personService;

    @Test
    void testDeleteAdmin() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").param("email", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAdmins() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterNewAdmin() throws Exception {
        AdminDTOIn adminDTOIn = new AdminDTOIn();
        adminDTOIn.setEmail("jane.doe@example.org");
        adminDTOIn.setName("Name");
        adminDTOIn.setPassword("iloveyou");
        adminDTOIn.setPhone_num(10);
        adminDTOIn.setRegionality("us-east-2");
        adminDTOIn.setToken("ABC123");
        String content = (new ObjectMapper()).writeValueAsString(adminDTOIn);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

