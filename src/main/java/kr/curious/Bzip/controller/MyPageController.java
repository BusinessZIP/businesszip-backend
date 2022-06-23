package kr.curious.Bzip.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import kr.curious.Bzip.Service.CardService;
import kr.curious.Bzip.utils.StatusCode;
import kr.curious.Bzip.vo.MemberIdVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/mypage")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
public class MyPageController {
    private final CardService cardService;

    @ResponseBody
    @PostMapping("")
    public String getAllMyCards(@RequestBody MemberIdVO memberIdVO)
    {
        JsonObject jsonObject = new JsonObject();

        //Status Code
        jsonObject.addProperty("code", StatusCode.OK);

        //My All Card
        JsonArray cardArray = new JsonArray();
        cardService.findAllByMemberId(memberIdVO.getId()).forEach(card -> {
            JsonObject cardObj = new JsonObject();

            //Tag Array
            JsonArray tagArray = new JsonArray();
            card.getTagList().forEach(tag -> {
                tagArray.add(tag.getText());
            });

            cardObj.addProperty("id", card.getId());
            cardObj.addProperty("background", card.getBackground());
            cardObj.addProperty("url", card.getUrl());
            cardObj.addProperty("job", card.getJob());
            cardObj.addProperty("phone", card.getPhoneNumber());
            cardObj.addProperty("email", card.getEmail());
            cardObj.addProperty("member_id", card.getMember().getId());
            cardObj.add("tags", tagArray);

            cardArray.add(cardObj);
        });

        jsonObject.add("mycards", cardArray);

        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping("post-card")
    public String createMyCard()
    {
        return "a";
    }
}
