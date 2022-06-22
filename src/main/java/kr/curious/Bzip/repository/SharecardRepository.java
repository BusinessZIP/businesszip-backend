package kr.curious.Bzip.repository;

import kr.curious.Bzip.model.entity.Member;
import kr.curious.Bzip.model.entity.Sharecard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharecardRepository extends JpaRepository<Sharecard, Long> {
}
