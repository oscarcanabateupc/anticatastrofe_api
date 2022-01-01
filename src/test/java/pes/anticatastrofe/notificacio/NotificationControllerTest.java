package pes.anticatastrofe.notificacio;

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
import pes.anticatastrofe.landmark.LandmarkService;
import pes.anticatastrofe.tag.TagService;

@ContextConfiguration(classes = {NotificationController.class})
@ExtendWith(SpringExtension.class)
class NotificationControllerTest {
    @MockBean
    private LandmarkService landmarkService;

    @Autowired
    private NotificationController notificationController;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private TagService tagService;

    @Test
    void testDeleteNotification() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.notificationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetNotifications() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.notificationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterNewNotification() throws Exception {
        NotificationDTOIn notificationDTOIn = new NotificationDTOIn();
        notificationDTOIn.setDescription("The characteristics of someone or something");
        notificationDTOIn.setId(1);
        notificationDTOIn.setLandmark_id(1);
        notificationDTOIn.setTag("Tag");
        String content = (new ObjectMapper()).writeValueAsString(notificationDTOIn);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.notificationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

