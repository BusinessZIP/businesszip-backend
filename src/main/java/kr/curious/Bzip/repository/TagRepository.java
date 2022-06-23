package kr.curious.Bzip.repository;

import kr.curious.Bzip.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    public List<Tag> findAllByTextContainingIgnoreCase(String text);
}
