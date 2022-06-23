package kr.curious.Bzip.repository;

import kr.curious.Bzip.model.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    public Optional<Memo> findByMemberIdAndCard_Id(Long memberId, Long cardId);
}
