package kr.curious.Bzip.Service;

import kr.curious.Bzip.model.entity.Card;
import kr.curious.Bzip.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Optional<Card> findById(Long cardId) { return cardRepository.findById(cardId); }
    public List<Card> findAllByMemberId(Long memberId) { return cardRepository.findAllByMemberId(memberId); }
    public Card save(Card card) { return cardRepository.save(card); }
}
