package kr.curious.Bzip.controller;

import com.google.gson.JsonObject;
import kr.curious.Bzip.model.entity.Member;
import kr.curious.Bzip.repository.MemberRepository;
import kr.curious.Bzip.utils.JwtUtil;
import kr.curious.Bzip.utils.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    private final JwtUtil jwtUtil;

    @ResponseBody
    @PostMapping("/login")
    public String Login(@RequestParam String userId, @RequestParam String password) throws Exception {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", StatusCode.UNAUTHORIZED);

        Optional<Member> memberOptional = memberRepository.findByEmailAndPassword(userId, password);

        memberOptional.ifPresent(member -> {
            jsonObject.addProperty("code", StatusCode.OK);
            jsonObject.addProperty("token", jwtUtil.createToken(member.getId().toString()));
        });
        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping("/signup")
    public String Signup(@RequestParam String email, @RequestParam String password, @RequestParam String name, @RequestParam String phoneNumber)
            throws Exception {
        JsonObject jsonObject = new JsonObject();


        Optional<Member> memberOptional = memberRepository.findByEmail(email);
        if(!memberOptional.isPresent()) {
            Member member = new Member();
            member.setEmail(email);
            member.setPassword(password);
            member.setName(name);
            member.setPhoneNumber(phoneNumber);
            member.setStatus("REGISTERED");
            member.setCreatedAt(LocalDateTime.now());
            memberRepository.save(member);
            jsonObject.addProperty("code", StatusCode.CREATED);
        } else {
            jsonObject.addProperty("code", StatusCode.UNAUTHORIZED);
        }

        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping("/test")
    public void Test(@RequestHeader("X-AUTH-TOKEN") String jwt) throws Exception {

        System.out.println(jwtUtil.getAuthentication(jwt).get().getName());
    }

}
