package kr.curious.Bzip.Service;

import kr.curious.Bzip.model.entity.Tag;
import kr.curious.Bzip.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag save(Tag tag) { return tagRepository.save(tag); }
    public List<Tag> findAllByWord(String word)
    {
        return tagRepository.findAllByTextContainingIgnoreCase(word);
    }
}
