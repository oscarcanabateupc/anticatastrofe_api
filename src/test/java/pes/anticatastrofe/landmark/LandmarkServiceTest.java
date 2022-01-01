package pes.anticatastrofe.landmark;

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
import pes.anticatastrofe.tag.Tag;

@ContextConfiguration(classes = {LandmarkService.class})
@ExtendWith(SpringExtension.class)
class LandmarkServiceTest {
    @MockBean
    private LandmarkRepository landmarkRepository;

    @Autowired
    private LandmarkService landmarkService;

    @Test
    void testGetLandmarks() {
        ArrayList<Landmark> landmarkList = new ArrayList<>();
        when(this.landmarkRepository.findAll()).thenReturn(landmarkList);
        List<Landmark> actualLandmarks = this.landmarkService.getLandmarks();
        assertSame(landmarkList, actualLandmarks);
        assertTrue(actualLandmarks.isEmpty());
        verify(this.landmarkRepository).findAll();
    }

    @Test
    void testAddNewLandmark() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");

        Tag tag = new Tag();
        tag.setColor(10.0f);
        tag.setDescription("The characteristics of someone or something");
        tag.setName("Name");

        Landmark landmark = new Landmark();
        landmark.setCoordinate_x(10.0f);
        landmark.setCoordinate_y(10.0f);
        landmark.setCreator(person);
        landmark.setDescription("The characteristics of someone or something");
        landmark.setId(1);
        landmark.setRadius(10.0f);
        landmark.setTag(tag);
        landmark.setTitle("Dr");
        landmark.set_area(true);
        when(this.landmarkRepository.save((Landmark) any())).thenReturn(landmark);

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        Tag tag1 = new Tag();
        tag1.setColor(10.0f);
        tag1.setDescription("The characteristics of someone or something");
        tag1.setName("Name");

        Landmark landmark1 = new Landmark();
        landmark1.setCoordinate_x(10.0f);
        landmark1.setCoordinate_y(10.0f);
        landmark1.setCreator(person1);
        landmark1.setDescription("The characteristics of someone or something");
        landmark1.setId(1);
        landmark1.setRadius(10.0f);
        landmark1.setTag(tag1);
        landmark1.setTitle("Dr");
        landmark1.set_area(true);
        assertSame(landmark, this.landmarkService.addNewLandmark(landmark1));
        verify(this.landmarkRepository).save((Landmark) any());
        assertTrue(this.landmarkService.getLandmarks().isEmpty());
    }

    @Test
    void testDeleteLandmark() {
        doNothing().when(this.landmarkRepository).deleteById((Integer) any());
        this.landmarkService.deleteLandmark(1);
        verify(this.landmarkRepository).deleteById((Integer) any());
        assertTrue(this.landmarkService.getLandmarks().isEmpty());
    }

    @Test
    void testGetLandmarkById() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");

        Tag tag = new Tag();
        tag.setColor(10.0f);
        tag.setDescription("The characteristics of someone or something");
        tag.setName("Name");

        Landmark landmark = new Landmark();
        landmark.setCoordinate_x(10.0f);
        landmark.setCoordinate_y(10.0f);
        landmark.setCreator(person);
        landmark.setDescription("The characteristics of someone or something");
        landmark.setId(1);
        landmark.setRadius(10.0f);
        landmark.setTag(tag);
        landmark.setTitle("Dr");
        landmark.set_area(true);
        Optional<Landmark> ofResult = Optional.of(landmark);
        when(this.landmarkRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Landmark> actualLandmarkById = this.landmarkService.getLandmarkById(1);
        assertSame(ofResult, actualLandmarkById);
        assertTrue(actualLandmarkById.isPresent());
        verify(this.landmarkRepository).findById((Integer) any());
        assertTrue(this.landmarkService.getLandmarks().isEmpty());
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

        Tag tag = new Tag();
        tag.setColor(10.0f);
        tag.setDescription("The characteristics of someone or something");
        tag.setName("Name");

        Landmark landmark = new Landmark();
        landmark.setCoordinate_x(10.0f);
        landmark.setCoordinate_y(10.0f);
        landmark.setCreator(person);
        landmark.setDescription("The characteristics of someone or something");
        landmark.setId(1);
        landmark.setRadius(10.0f);
        landmark.setTag(tag);
        landmark.setTitle("Dr");
        landmark.set_area(true);
        Optional<Landmark> ofResult = Optional.of(landmark);
        when(this.landmarkRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Landmark> actualFindByIDResult = this.landmarkService.findByID(1);
        assertSame(ofResult, actualFindByIDResult);
        assertTrue(actualFindByIDResult.isPresent());
        verify(this.landmarkRepository).findById((Integer) any());
        assertTrue(this.landmarkService.getLandmarks().isEmpty());
    }
}

