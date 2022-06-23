package kr.curious.Bzip.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.curious.Bzip.Service.MemberService;
import kr.curious.Bzip.model.entity.Member;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {

    public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * 24;

    private final MemberService memberService;

    private String secretKey = "bzip";

    public JwtUtil(MemberService memberService) {
        this.memberService = memberService;
    }

    public String createToken(String userPk) {
        Date now = new Date();
        Claims claims = Jwts.claims().setSubject("access_token")
                .setIssuedAt(now)// 토큰 발행일자;
                .setExpiration(new Date(now.getTime() + JWT_TOKEN_VALIDITY)); // 토큰 유효시간 설정
        claims.put("uid", userPk);
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, 암호키
                .compact();
    }

    // Jwt 토큰으로 인증 정보 조회
    public Optional<Member> getAuthentication(String token) throws Exception {
        String userPk = this.getUserPk(token);
        Optional<Member> member = memberService.loadUserByUsername(userPk);
        return member;
    }

    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) throws Exception{
        try {
            return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("uid");
        } catch (Exception e) {
            return null;
        }
    }


    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
