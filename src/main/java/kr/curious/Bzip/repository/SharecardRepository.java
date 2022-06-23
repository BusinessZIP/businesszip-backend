package kr.curious.Bzip.repository;

import kr.curious.Bzip.model.entity.Member;
import kr.curious.Bzip.model.entity.Sharecard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharecardRepository extends JpaRepository<Sharecard, Long> {
    public List<Sharecard> findAllByMemberId(Long memberId);
}
