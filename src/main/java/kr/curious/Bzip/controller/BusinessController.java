package kr.curious.Bzip.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import kr.curious.Bzip.Service.CardService;
import kr.curious.Bzip.Service.SharecardService;
import kr.curious.Bzip.model.entity.Card;
import kr.curious.Bzip.utils.JwtUtil;
import kr.curious.Bzip.utils.StatusCode;
import kr.curious.Bzip.vo.CardIdVO;
import kr.curious.Bzip.vo.MemberIdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/bcard")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
@Log4j2
public class BusinessController {

    private final SharecardService sharecardService;
    private final CardService cardService;
    private final JwtUtil jwtUtil;

    @ResponseBody
    @PostMapping("")
    public String getAllUsersCard(@RequestHeader("X-AUTH-TOKEN") String jwt) throws Exception
    {
        JsonObject jsonObject = new JsonObject();

        if(jwt == null) { jsonObject.addProperty("code", StatusCode.NOT_FOUND); return jsonObject.toString(); }

        //Status Code
        jsonObject.addProperty("code", StatusCode.OK);

        //AllUsers Info
        JsonArray userArray = new JsonArray();

        sharecardService.findAllByMemberId(jwtUtil.getAuthentication(jwt).get().getId()).forEach(sharecard -> {
            JsonObject userObj = new JsonObject();

            //Tag Array
            JsonArray tagArray = new JsonArray();
            sharecard.getCard().getTagList().forEach(tag -> {
                tagArray.add(tag.getText());
            });

            userObj.addProperty("id", sharecard.getCard().getId());
            userObj.addProperty("background", sharecard.getCard().getBackground());
            userObj.addProperty("url", sharecard.getCard().getUrl());
            userObj.addProperty("job", sharecard.getCard().getJob());
            userObj.addProperty("phone", sharecard.getCard().getPhoneNumber());
            userObj.addProperty("email", sharecard.getCard().getEmail());
            userObj.addProperty("address", sharecard.getCard().getAddress());
            userObj.addProperty("member_id", sharecard.getCard().getMember().getId());
            userObj.add("tags", tagArray);

            userArray.add(userObj);
        });

        jsonObject.add("users", userArray);

        return jsonObject.toString();
    }

    @ResponseBody
    @PostMapping("/info")
    public String getUserCardDetail(@RequestBody CardIdVO cardIdVO)
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
