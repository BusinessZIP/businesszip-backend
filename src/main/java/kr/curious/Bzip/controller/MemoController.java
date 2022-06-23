package kr.curious.Bzip.controller;

import com.google.gson.JsonObject;
import kr.curious.Bzip.Service.CardService;
import kr.curious.Bzip.Service.MemoService;
import kr.curious.Bzip.model.entity.Member;
import kr.curious.Bzip.model.entity.Memo;
import kr.curious.Bzip.utils.JwtUtil;
import kr.curious.Bzip.utils.StatusCode;
import kr.curious.Bzip.vo.MemoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/memo")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
@Log4j2
public class MemoController {
    private final MemoService memoService;
    private final CardService cardService;

    private final JwtUtil jwtUtil;

    @ResponseBody
    @PostMapping("")
    public String postMemo(@RequestHeader("X-AUTH-TOKEN") String jwt, @RequestBody MemoVO memoVO) throws Exception
    {
        JsonObject jsonObject = new JsonObject();

        if(jwt == null) { jsonObject.addProperty("code", StatusCode.NOT_FOUND); return jsonObject.toString(); }

        Optional<Memo> memo = memoService.findByMemberIdAndCard_Id(jwtUtil.getAuthentication(jwt).get().getId(), memoVO.getId());

        //create NEW memo & update OLD memo
        if(!memo.isPresent())
        {
            //Status Code
            jsonObject.addProperty("code", StatusCode.CREATED);

            Memo newMemo = new Memo();
            newMemo.setMemberId(jwtUtil.getAuthentication(jwt).get().getId());
            newMemo.setContent(memoVO.getContent());
            cardService.findById(memoVO.getId()).ifPresent( card -> {
                newMemo.setCard(card);
            });

            memoService.save(newMemo);
        }
        else
        {
            //Status Code
            jsonObject.addProperty("code", StatusCode.OK);

            memo.ifPresent(originMemo -> {
                originMemo.setContent(memoVO.getContent());

                memoService.save(originMemo);
            });
        }

        return jsonObject.toString();
    }


}
