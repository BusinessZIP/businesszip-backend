package kr.curious.Bzip.repository;

import kr.curious.Bzip.BzipApplicationTests;
import kr.curious.Bzip.model.entity.Card;
import kr.curious.Bzip.model.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class CardRepositoryTest extends BzipApplicationTests {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void create() {

        Member member = new Member();
        member.setEmail("user03@naver.com");
        member.setPassword("user03");
        member.setName("user03");
        member.setPhoneNumber("010-1111-2222");
        member.setStatus("REGISTERED");
        member.setCreatedAt(LocalDateTime.now());
        memberRepository.save(member);

        Card card = new Card();
        card.setBackground("user1234");
        card.setQrCode("qrqrqr");
        card.setMember(member);

        Card newCard = cardRepository.save(card);
        Assertions.assertNotNull(newCard);
    }


}
