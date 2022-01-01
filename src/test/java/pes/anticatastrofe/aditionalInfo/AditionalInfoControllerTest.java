package pes.anticatastrofe.aditionalInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
import pes.anticatastrofe.person.PersonService;

@ContextConfiguration(classes = {AditionalInfoController.class})
@ExtendWith(SpringExtension.class)
class AditionalInfoControllerTest {
    @Autowired
    private AditionalInfoController aditionalInfoController;

    @MockBean
    private AditionalInfoService aditionalInfoService;

    @MockBean
    private PersonService personService;

    @Test
    void testDeleteAditionalInfo() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").param("email", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.aditionalInfoController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAditionalInfos() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.aditionalInfoController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterNewAditionalInfo() throws Exception {
        AditionalInfoDTOIn aditionalInfoDTOIn = new AditionalInfoDTOIn();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        aditionalInfoDTOIn.setBirth_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        aditionalInfoDTOIn.setBlood_type("Blood type");
        aditionalInfoDTOIn.setCity("Oxford");
        aditionalInfoDTOIn.setCountry("GB");
        aditionalInfoDTOIn.setEmail("jane.doe@example.org");
        aditionalInfoDTOIn.setPath_profile_pic("Path profile pic");
        aditionalInfoDTOIn.setPostal_code("Postal code");
        aditionalInfoDTOIn.setState("MD");
        aditionalInfoDTOIn.setStreet("Street");
        String content = (new ObjectMapper()).writeValueAsString(aditionalInfoDTOIn);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.aditionalInfoController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

