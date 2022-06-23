package kr.curious.Bzip.repository;

import kr.curious.Bzip.model.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
