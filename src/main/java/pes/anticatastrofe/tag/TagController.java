package pes.anticatastrofe.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/tag")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<TagDTO> getTags() {
        List<Tag> pins = tagService.getTags();
        return pins.stream()
                .map(TagDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerNewTag(@RequestBody Tag tag) {
        Map<String, String> response = new HashMap<>();
        if (!tagService.findByID(tag.name).isPresent()) {
            Tag t = tagService.addNewTag(tag);
            response.put("operation_success", "true");
            response.put("new_tag_id", t.name);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "tag already exists");
            response.put("status", HttpStatus.ALREADY_REPORTED.toString());
            return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteTag(@RequestParam String name) {
        Map<String, String> response = new HashMap<>();
        if (tagService.findByID(name).isPresent()) {
            tagService.deleteTag(name);
            response.put("operation_success", "true");
            response.put("deleted_tag_id", name);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "tag not exists so nothing was deleted");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}