package kr.curious.Bzip.Service;

import kr.curious.Bzip.model.entity.Member;
import kr.curious.Bzip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member Login(String email, String password) {
        Member member = memberRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new RuntimeException());

        return member;
    }

    public Optional<Member> loadUserByUsername(String userPk) {
        Optional<Member> member = memberRepository.findById(Long.valueOf(userPk));
        return member;
    }
    public Optional<Member> findById(Long id) { return memberRepository.findById(id); }
}
