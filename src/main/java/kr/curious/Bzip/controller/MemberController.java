package kr.curious.Bzip.controller;

import com.google.gson.JsonObject;
import kr.curious.Bzip.model.entity.Member;
import kr.curious.Bzip.repository.MemberRepository;
import kr.curious.Bzip.utils.JwtUtil;
import kr.curious.Bzip.utils.StatusCode;
import kr.curious.Bzip.vo.LoginVO;
import kr.curious.Bzip.vo.SignUpVO;
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
    public String Login(@RequestBody LoginVO loginVO) throws Exception {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", StatusCode.UNAUTHORIZED);

        Optional<Member> memberOptional = memberRepository.findByEmailAndPassword(loginVO.getEmail(), loginVO.getPassword());

        memberOptional.ifPresent(member -> {
            jsonObject.addProperty("code", StatusCode.OK);
            jsonObject.addProperty("token", jwtUtil.createToken(member.getId().toString()));
        });
        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping("/signup")
    public String Signup(@RequestBody SignUpVO signUpVO)
            throws Exception {
        JsonObject jsonObject = new JsonObject();

        Optional<Member> memberOptional = memberRepository.findByEmail(signUpVO.getEmail());
        if(!memberOptional.isPresent()) {
            Member member = new Member();
            member.setEmail(signUpVO.getEmail());
            member.setPassword(signUpVO.getPassword());
            member.setName(signUpVO.getName());
            member.setPhoneNumber(signUpVO.getPhone());
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
        System.out.println(jwtUtil.getAuthentication(jwt).get().getId());
        System.out.println(jwtUtil.getAuthentication(jwt).get().getName());
    }

}
