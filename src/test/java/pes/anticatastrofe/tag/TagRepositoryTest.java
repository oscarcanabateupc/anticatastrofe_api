package pes.anticatastrofe.tag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TagRepositoryTest {

    @Autowired
    TagRepository testTagRepository;

    @Test
    void testInsert() {
        String tag_id = "test_tag";
        Tag t = new Tag(tag_id,"a");
        t.setName(tag_id);

        testTagRepository.save(t);
        Boolean exist = testTagRepository.existsById(tag_id);
        assert exist;
    }

    @Test
    void testSelect() {
        String tag_id_1 = "test_tag";
        String tag_id_2 = "non_existent_test_tag";
        Tag t = new Tag(tag_id_1,"a");
        t.setName(tag_id_1);

        testTagRepository.save(t);
        Boolean exist_1 = testTagRepository.existsById(tag_id_1);
        Boolean exist_2 = testTagRepository.existsById(tag_id_2);
        assert exist_1;
        assert !exist_2;
    }

    @Test
    void testDelete() {
        String tag_id = "test_tag";
        Tag t = new Tag(tag_id,"a");
        t.setName(tag_id);

        testTagRepository.save(t);
        testTagRepository.deleteById(tag_id);
        Boolean exist = testTagRepository.existsById(tag_id);
        assert !exist;
    }
}