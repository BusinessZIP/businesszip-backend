package kr.curious.Bzip.repository;

import kr.curious.Bzip.BzipApplicationTests;
import kr.curious.Bzip.model.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberRepositoryTest extends BzipApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void create() {

        Member member = new Member();
        member.setEmail("user02@naver.com");
        member.setPassword("user01");
        member.setName("user01");
        member.setPhoneNumber("010-1111-2222");
        member.setStatus("REGISTERED");
        member.setCreatedAt(LocalDateTime.now());

        Member newMember = memberRepository.save(member);
        Assertions.assertNotNull(newMember);
    }

    @Test
    @Transactional
    public void read() {
        Optional<Member> memberOptional = memberRepository.findByEmailAndPassword("user02@naver.com","user01");
        System.out.println("------------------find-----------------");
        memberOptional.ifPresent(member -> System.out.println(member.getName()));
    }

}
