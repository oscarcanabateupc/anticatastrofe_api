package pes.anticatastrofe.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, String>> registerNewTag(@RequestBody Tag tag){
        Map<String, String> response = new HashMap<String, String>();
        if(!tagService.findByID(tag.name).isPresent()) {
            Tag t = tagService.addNewTag(tag);
            response.put("operation_success", "true");
            response.put("new_tag_id",t.name);
            response.put("operation_success", "true");
            response.put("deleted_object_id", t.name);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("message", "aditionalInfo already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
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