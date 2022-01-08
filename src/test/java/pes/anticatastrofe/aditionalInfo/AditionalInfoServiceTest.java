package pes.anticatastrofe.aditionalInfo;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pes.anticatastrofe.person.Person;

@ContextConfiguration(classes = {AditionalInfoService.class})
@ExtendWith(SpringExtension.class)
class AditionalInfoServiceTest {
    @MockBean
    private AditionalInfoRepository aditionalInfoRepository;

    @Autowired
    private AditionalInfoService aditionalInfoService;

    @Test
    void testGetAditionalInfos() {
        ArrayList<AditionalInfo> aditionalInfoList = new ArrayList<>();
        when(this.aditionalInfoRepository.findAll()).thenReturn(aditionalInfoList);
        List<AditionalInfo> actualAditionalInfos = this.aditionalInfoService.getAditionalInfos();
        assertSame(aditionalInfoList, actualAditionalInfos);
        assertTrue(actualAditionalInfos.isEmpty());
        verify(this.aditionalInfoRepository).findAll();
    }

    @Test
    void testAddNewAditionalInfo() {
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setLandmark(new ArrayList<>());
        person.setName("Name");
        person.setPassword("iloveyou");
        person.setPhone_num(10);
        person.setToken("ABC123");

        AditionalInfo aditionalInfo = new AditionalInfo();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        aditionalInfo.setBlood_type("Blood type");
        aditionalInfo.setCity("Oxford");
        aditionalInfo.setCountry("GB");
        aditionalInfo.setEmail("jane.doe@example.org");
        aditionalInfo.setPath_profile_pic("Path profile pic");
        aditionalInfo.setPerson(person);
        aditionalInfo.setPostal_code("Postal code");
        aditionalInfo.setState("MD");
        aditionalInfo.setStreet("Street");
        when(this.aditionalInfoRepository.save((AditionalInfo) any())).thenReturn(aditionalInfo);

        Person person1 = new Person();
        person1.setEmail("jane.doe@example.org");
        person1.setLandmark(new ArrayList<>());
        person1.setName("Name");
        person1.setPassword("iloveyou");
        person1.setPhone_num(10);
        person1.setToken("ABC123");

        AditionalInfo aditionalInfo1 = new AditionalInfo();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        aditionalInfo1.setBlood_type("Blood type");
        aditionalInfo1.setCity("Oxford");
        aditionalInfo1.setCountry("GB");
        aditionalInfo1.setEmail("jane.doe@example.org");
        aditionalInfo1.setPath_profile_pic("Path profile pic");
        aditionalInfo1.setPerson(person1);
        aditionalInfo1.setPostal_code("Postal code");
        aditionalInfo1.setState("MD");
        aditionalInfo1.setStreet("Street");
        assertSame(aditionalInfo, this.aditionalInfoService.addNewAditionalInfo(aditionalInfo1));
        verify(this.aditionalInfoRepository).save((AditionalInfo) any());
        assertTrue(this.aditionalInfoService.getAditionalInfos().isEmpty());
    }

    @Test
    void testDeleteAditionalInfo() {
        doNothing().when(this.aditionalInfoRepository).deleteById((String) any());
        this.aditionalInfoService.deleteAditionalInfo("jane.doe@example.org");
        verify(this.aditionalInfoRepository).deleteById((String) any());
        assertTrue(this.aditionalInfoService.getAditionalInfos().isEmpty());
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

        AditionalInfo aditionalInfo = new AditionalInfo();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        aditionalInfo.setBlood_type("Blood type");
        aditionalInfo.setCity("Oxford");
        aditionalInfo.setCountry("GB");
        aditionalInfo.setEmail("jane.doe@example.org");
        aditionalInfo.setPath_profile_pic("Path profile pic");
        aditionalInfo.setPerson(person);
        aditionalInfo.setPostal_code("Postal code");
        aditionalInfo.setState("MD");
        aditionalInfo.setStreet("Street");
        Optional<AditionalInfo> ofResult = Optional.of(aditionalInfo);
        when(this.aditionalInfoRepository.findById((String) any())).thenReturn(ofResult);
        Optional<AditionalInfo> actualFindByIDResult = this.aditionalInfoService.findByID("jane.doe@example.org");
        assertSame(ofResult, actualFindByIDResult);
        assertTrue(actualFindByIDResult.isPresent());
        verify(this.aditionalInfoRepository).findById((String) any());
        assertTrue(this.aditionalInfoService.getAditionalInfos().isEmpty());
    }
}

