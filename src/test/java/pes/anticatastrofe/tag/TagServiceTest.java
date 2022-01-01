package pes.anticatastrofe.tag;

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

@ContextConfiguration(classes = {TagService.class})
@ExtendWith(SpringExtension.class)
class TagServiceTest {
    @MockBean
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    @Test
    void testGetTags() {
        ArrayList<Tag> tagList = new ArrayList<>();
        when(this.tagRepository.findAll()).thenReturn(tagList);
        List<Tag> actualTags = this.tagService.getTags();
        assertSame(tagList, actualTags);
        assertTrue(actualTags.isEmpty());
        verify(this.tagRepository).findAll();
    }

    @Test
    void testGetTagById() {
        Tag tag = new Tag();
        tag.setColor(10.0f);
        tag.setDescription("The characteristics of someone or something");
        tag.setName("Name");
        Optional<Tag> ofResult = Optional.of(tag);
        when(this.tagRepository.findById((String) any())).thenReturn(ofResult);
        Optional<Tag> actualTagById = this.tagService.getTagById("Name");
        assertSame(ofResult, actualTagById);
        assertTrue(actualTagById.isPresent());
        verify(this.tagRepository).findById((String) any());
        assertTrue(this.tagService.getTags().isEmpty());
    }

    @Test
    void testAddNewTag() {
        Tag tag = new Tag();
        tag.setColor(10.0f);
        tag.setDescription("The characteristics of someone or something");
        tag.setName("Name");
        when(this.tagRepository.save((Tag) any())).thenReturn(tag);

        Tag tag1 = new Tag();
        tag1.setColor(10.0f);
        tag1.setDescription("The characteristics of someone or something");
        tag1.setName("Name");
        assertSame(tag, this.tagService.addNewTag(tag1));
        verify(this.tagRepository).save((Tag) any());
        assertTrue(this.tagService.getTags().isEmpty());
    }

    @Test
    void testDeleteTag() {
        doNothing().when(this.tagRepository).deleteById((String) any());
        this.tagService.deleteTag("Name");
        verify(this.tagRepository).deleteById((String) any());
        assertTrue(this.tagService.getTags().isEmpty());
    }

    @Test
    void testFindByID() {
        Tag tag = new Tag();
        tag.setColor(10.0f);
        tag.setDescription("The characteristics of someone or something");
        tag.setName("Name");
        Optional<Tag> ofResult = Optional.of(tag);
        when(this.tagRepository.findById((String) any())).thenReturn(ofResult);
        Optional<Tag> actualFindByIDResult = this.tagService.findByID("Name");
        assertSame(ofResult, actualFindByIDResult);
        assertTrue(actualFindByIDResult.isPresent());
        verify(this.tagRepository).findById((String) any());
        assertTrue(this.tagService.getTags().isEmpty());
    }
}

