package kr.curious.Bzip.repository;

import kr.curious.Bzip.BzipApplicationTests;
import kr.curious.Bzip.model.entity.Card;
import kr.curious.Bzip.model.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        member.setId(Long.valueOf(2));
        member.setEmail("user02@naver.com");
        member.setPassword("user02");
        member.setName("user02");
        member.setPhoneNumber("010-1111-2222");
        member.setStatus("REGISTERED");
        member.setCreatedAt(LocalDateTime.now());
        //memberRepository.save(member);

        Card card = new Card();
        card.setBackground("user1234567890");
        card.setUrl("https://qwewewe.com");
        card.setName("user567890");
        card.setJob("developer");
        card.setEmail("user@123.com");
        card.setMember(member);

        Card newCard = cardRepository.save(card);
        Assertions.assertNotNull(newCard);
    }


}
