package pes.anticatastrofe.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pes.anticatastrofe.pin.Pin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/tag")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDTO> getTags() {
        List<Tag> pins = tagService.getTags();
        List<TagDTO> tagsDTO = pins.stream()
                .map(tag->new TagDTO(tag))
                .collect(Collectors.toList());
        return tagsDTO;
    }

    @PostMapping
    public Map<String, String> registerNewTag(@RequestBody Tag tag){
        Tag t = tagService.addNewTag(tag);
        Map<String, String> map = new HashMap<String, String>();
        map.put("operation_success", "true");
        map.put("new_tag_id",t.name);
        return map;
    }

    @DeleteMapping
    public Map<String,String> deleteTag(@RequestParam String name) {
        tagService.deleteTag(name);
        Map<String, String> map = new HashMap<String, String>();
        map.put("operation_success", "true");
        map.put("deleted_tag_id",name);
        return map;
    }
}