package pes.anticatastrofe.user;

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

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private AditionalInfoService aditionalInfoService;

    @MockBean
    private PersonService personService;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void testDeleteUser() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").param("email", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testDeleteUser2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").param("email", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetUsers() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetUsers2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterNewUser() throws Exception {
        UserDTOIn userDTOIn = new UserDTOIn();
        userDTOIn.setEmail("jane.doe@example.org");
        userDTOIn.setLast_coordinate_x(10.0f);
        userDTOIn.setLast_coordinate_y(10.0f);
        userDTOIn.setName("Name");
        userDTOIn.setPassword("iloveyou");
        userDTOIn.setPhone_num(10);
        userDTOIn.setToken("ABC123");
        String content = (new ObjectMapper()).writeValueAsString(userDTOIn);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterNewUser2() throws Exception {
        UserDTOIn userDTOIn = new UserDTOIn();
        userDTOIn.setEmail("jane.doe@example.org");
        userDTOIn.setLast_coordinate_x(10.0f);
        userDTOIn.setLast_coordinate_y(10.0f);
        userDTOIn.setName("Name");
        userDTOIn.setPassword("iloveyou");
        userDTOIn.setPhone_num(10);
        userDTOIn.setToken("ABC123");
        String content = (new ObjectMapper()).writeValueAsString(userDTOIn);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testUpdateUserPosition() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/").param("email", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("last_coordinate_x", String.valueOf(10.0f));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("last_coordinate_y", String.valueOf(10.0f));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testUpdateUserPosition2() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.get("/").param("email", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("last_coordinate_x", String.valueOf(10.0f));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("last_coordinate_y", String.valueOf(10.0f));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

