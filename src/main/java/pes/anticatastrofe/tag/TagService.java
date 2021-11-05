package pes.anticatastrofe.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findById(name);
    }

    public Tag addNewTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public void deleteTag(String name) {
        tagRepository.deleteById(name);
    }
}
