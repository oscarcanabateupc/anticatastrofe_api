package pes.anticatastrofe.person;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PersonController.class})
@ExtendWith(SpringExtension.class)
class PersonControllerTest {
    @Autowired
    private PersonController personController;

    @MockBean
    private PersonService personService;

    @Test
    void testGetPersons() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetToken() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getToken")
                .param("email", "foo")
                .param("introduced_password", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testResetToken() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/resetToken")
                .param("email", "foo")
                .param("introduced_token", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testUserPasswordMatch() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userPasswordMatch")
                .param("email", "foo")
                .param("introduced_password", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

