package pes.anticatastrofe.landmark;

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
import pes.anticatastrofe.person.PersonService;
import pes.anticatastrofe.tag.TagService;

@ContextConfiguration(classes = {LandmarkController.class})
@ExtendWith(SpringExtension.class)
class LandmarkControllerTest {
    @Autowired
    private LandmarkController landmarkController;

    @MockBean
    private LandmarkService landmarkService;

    @MockBean
    private PersonService personService;

    @MockBean
    private TagService tagService;

    @Test
    void testDeleteLandmark() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("landmark_id", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.landmarkController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetLandmarks() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.landmarkController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterNewLandmark() throws Exception {
        LandMarkDTOin landMarkDTOin = new LandMarkDTOin();
        landMarkDTOin.setCoordinate_x(10.0f);
        landMarkDTOin.setCoordinate_y(10.0f);
        landMarkDTOin.setCreator_email("jane.doe@example.org");
        landMarkDTOin.setDescription("The characteristics of someone or something");
        landMarkDTOin.setId(1);
        landMarkDTOin.setTag_name("Tag name");
        landMarkDTOin.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(landMarkDTOin);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.landmarkController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

