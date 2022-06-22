package kr.curious.Bzip.repository;

import kr.curious.Bzip.BzipApplicationTests;
import kr.curious.Bzip.model.entity.Card;
import kr.curious.Bzip.model.entity.Member;
import kr.curious.Bzip.model.entity.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TagRepositoryTest extends BzipApplicationTests {

    @Autowired
    private TagRepository tagRepository;

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

        Tag tag = new Tag();
        tag.setText("카카오");
        tag.setCard(newCard);
        Tag newTag = tagRepository.save(tag);
        Assertions.assertNotNull(newTag);
    }


}
