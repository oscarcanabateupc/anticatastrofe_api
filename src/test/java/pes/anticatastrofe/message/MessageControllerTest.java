package pes.anticatastrofe.message;

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

@ContextConfiguration(classes = {MessageController.class})
@ExtendWith(SpringExtension.class)
class MessageControllerTest {
    @Autowired
    private MessageController messageController;

    @MockBean
    private MessageService messageService;

    @MockBean
    private PersonService personService;

    @Test
    void testDeleteMessage() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.messageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetMessages() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.messageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterNewMessage() throws Exception {
        MessageDTOIn messageDTOIn = new MessageDTOIn();
        messageDTOIn.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        messageDTOIn.setDate_sent(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        messageDTOIn.setId(1);
        messageDTOIn.setRecipient_email("jane.doe@example.org");
        messageDTOIn.setSeen(true);
        messageDTOIn.setSender_email("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(messageDTOIn);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.messageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

