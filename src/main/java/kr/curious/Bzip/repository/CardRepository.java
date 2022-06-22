package kr.curious.Bzip.repository;

import kr.curious.Bzip.model.entity.Card;
import kr.curious.Bzip.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
