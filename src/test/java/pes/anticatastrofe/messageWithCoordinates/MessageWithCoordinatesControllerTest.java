package pes.anticatastrofe.messageWithCoordinates;

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
import pes.anticatastrofe.landmark.LandmarkService;
import pes.anticatastrofe.message.MessageService;
import pes.anticatastrofe.person.PersonService;

@ContextConfiguration(classes = {MessageWithCoordinatesController.class})
@ExtendWith(SpringExtension.class)
class MessageWithCoordinatesControllerTest {
    @MockBean
    private LandmarkService landmarkService;

    @MockBean
    private MessageService messageService;

    @Autowired
    private MessageWithCoordinatesController messageWithCoordinatesController;

    @MockBean
    private MessageWithCoordinatesService messageWithCoordinatesService;

    @MockBean
    private PersonService personService;

    @Test
    void testDeleteMessage() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.messageWithCoordinatesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetMessages() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.messageWithCoordinatesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterNewMessage() throws Exception {
        MessageWithCoordinatesDTOIn messageWithCoordinatesDTOIn = new MessageWithCoordinatesDTOIn();
        messageWithCoordinatesDTOIn.setContent("Not all who wander are lost");
        messageWithCoordinatesDTOIn.setCreator_email("jane.doe@example.org");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        messageWithCoordinatesDTOIn.setDate_sent(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        messageWithCoordinatesDTOIn.setId(1);
        messageWithCoordinatesDTOIn.setLandmark_id(1);
        messageWithCoordinatesDTOIn.setRecipient_email("jane.doe@example.org");
        messageWithCoordinatesDTOIn.setSeen(true);
        messageWithCoordinatesDTOIn.setSender_email("jane.doe@example.org");
        messageWithCoordinatesDTOIn.setTag("Tag");
        String content = (new ObjectMapper()).writeValueAsString(messageWithCoordinatesDTOIn);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.messageWithCoordinatesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

