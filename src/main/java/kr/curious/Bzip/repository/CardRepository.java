package kr.curious.Bzip.repository;

import kr.curious.Bzip.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    public Optional<Card> findById(Long cardId);
    public List<Card> findAllByMemberId(Long memberId);
    public List<Card> findAllByEmailContainingIgnoreCaseOrJobContainingIgnoreCaseOrNameContainingIgnoreCaseOrPhoneNumberContainingIgnoreCaseOrAddressContainingIgnoreCase(String email, String job, String name, String phone, String address);
}
