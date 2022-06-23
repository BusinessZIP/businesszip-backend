package kr.curious.Bzip.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import kr.curious.Bzip.Service.CardService;
import kr.curious.Bzip.Service.MemberService;
import kr.curious.Bzip.Service.TagService;
import kr.curious.Bzip.model.entity.Card;
import kr.curious.Bzip.model.entity.Member;
import kr.curious.Bzip.model.entity.Tag;
import kr.curious.Bzip.utils.StatusCode;
import kr.curious.Bzip.vo.CardIdVO;
import kr.curious.Bzip.vo.CardVO;
import kr.curious.Bzip.vo.MemberIdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("api/v1/mypage")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
@Log4j2
public class MyPageController {
    private final CardService cardService;
    private final MemberService memberService;
    private final TagService tagService;

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
            cardObj.addProperty("address", card.getAddress());
            cardObj.addProperty("member_id", card.getMember().getId());
            cardObj.add("tags", tagArray);

            cardArray.add(cardObj);
        });

        jsonObject.add("mycards", cardArray);

        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping("post-card")
    public String createMyCard(@RequestBody CardVO cardVO)
    {
        JsonObject jsonObject = new JsonObject();

        AtomicReference<Card> cardByTag = new AtomicReference<>(new Card());
        Optional<Member> oMember = memberService.findById(cardVO.getId());

        oMember.ifPresent(member -> {

            //Create Card
            Card card = new Card();
            card.setName(cardVO.getName());
            card.setJob(cardVO.getJob());
            card.setBackground(cardVO.getBackground());
            card.setUrl(cardVO.getUrl());
            card.setEmail(cardVO.getEmail());
            card.setPhoneNumber(cardVO.getPhone());
            card.setAddress(cardVO.getAddress());
            card.setMember(member);

            cardByTag.set(cardService.save(card));
        });

        cardVO.getTags().forEach( tagText -> {
            Tag tag = new Tag();
            tag.setText(tagText.toString());
            tag.setCard(cardByTag.get());
            tagService.save(tag);
        });

        jsonObject.addProperty("code", StatusCode.CREATED);

        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping("info")
    public String getMyCardDetail(@RequestBody CardIdVO cardIdVO)
    {
        JsonObject jsonObject = new JsonObject();

        //Status Code
        jsonObject.addProperty("code", StatusCode.OK);

        Optional<Card> card = cardService.findById(cardIdVO.getId());

        //Tag Array
        JsonArray tagArray = new JsonArray();
        card.get().getTagList().forEach(tag -> {
            tagArray.add(tag.getText());
        });

        //User Info
        JsonObject userObj = new JsonObject();
        userObj.addProperty("id", card.get().getId());
        userObj.addProperty("background", card.get().getBackground());
        userObj.addProperty("url", card.get().getUrl());
        userObj.addProperty("job", card.get().getJob());
        userObj.addProperty("phone", card.get().getPhoneNumber());
        userObj.addProperty("email", card.get().getEmail());
        userObj.addProperty("address", card.get().getAddress());
        userObj.addProperty("member_id", card.get().getMember().getId());;
        userObj.add("tags", tagArray);

        jsonObject.add("user", userObj);

        return jsonObject.toString();
    }

}