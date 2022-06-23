package kr.curious.Bzip.Service;

import kr.curious.Bzip.model.entity.Tag;
import kr.curious.Bzip.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag save(Tag tag) { return tagRepository.save(tag); }
}
