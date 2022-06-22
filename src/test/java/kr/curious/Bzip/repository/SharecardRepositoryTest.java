package kr.curious.Bzip.repository;

import kr.curious.Bzip.BzipApplicationTests;
import kr.curious.Bzip.model.entity.Card;
import kr.curious.Bzip.model.entity.Member;
import kr.curious.Bzip.model.entity.Sharecard;
import kr.curious.Bzip.model.entity.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class SharecardRepositoryTest extends BzipApplicationTests {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SharecardRepository sharecardRepository;

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
        card.setBackground("user12345");
        card.setUrl("https://qwewewe.com");
        card.setName("user");
        card.setJob("developer");
        card.setEmail("user@123.com");
        card.setMember(member);
        cardRepository.save(card);

        Sharecard sharecard = new Sharecard();
        sharecard.setMemberId(5L);
        sharecard.setCard(card);
        Sharecard sharecard1 = sharecardRepository.save(sharecard);
        Assertions.assertNotNull(sharecard1);
    }

}
