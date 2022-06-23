package kr.curious.Bzip.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import kr.curious.Bzip.Service.CardService;
import kr.curious.Bzip.Service.SharecardService;
import kr.curious.Bzip.Service.TagService;
import kr.curious.Bzip.model.entity.Card;
import kr.curious.Bzip.utils.JwtUtil;
import kr.curious.Bzip.utils.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/search")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
@Log4j2
public class SearchController {

    private final SharecardService sharecardService;
    private final CardService cardService;
    private final TagService tagService;

    private final JwtUtil jwtUtil;

    @ResponseBody
    @GetMapping("info")
    public String searchInfoByWord(@RequestHeader("X-AUTH-TOKEN") String jwt, @RequestParam String word) throws Exception
    {
        JsonObject jsonObject = new JsonObject();

        if(jwt == null) { jsonObject.addProperty("code", StatusCode.NOT_FOUND); return jsonObject.toString(); }

        //Status Code
        jsonObject.addProperty("code", StatusCode.OK);

        //find registered another person's cards
        Set<Long> registeredUserCards = new HashSet<>();
        sharecardService.findAllByMemberId(jwtUtil.getAuthentication(jwt).get().getId()).forEach(sharecard -> {
            registeredUserCards.add(sharecard.getCard().getId());
        });

        //search keyword
        List<Card> cardList = cardService.findAllByWord(word, word, word, word, word);

        //AllUsers Info
        JsonArray userArray = new JsonArray();
        cardList.forEach(card -> {
            if(registeredUserCards.contains(card.getId()))
            {
                JsonObject userObj = new JsonObject();

                //Tag Array
                JsonArray tagArray = new JsonArray();
                card.getTagList().forEach(tag -> {
                    tagArray.add(tag.getText());
                });

                userObj.addProperty("id", card.getId());
                userObj.addProperty("background", card.getBackground());
                userObj.addProperty("url", card.getUrl());
                userObj.addProperty("job", card.getJob());
                userObj.addProperty("phone", card.getPhoneNumber());
                userObj.addProperty("email", card.getEmail());
                userObj.addProperty("address", card.getAddress());
                userObj.addProperty("member_id", card.getMember().getId());
                userObj.add("tags", tagArray);

                userArray.add(userObj);

            }
        });

        jsonObject.add("users", userArray);

        return jsonObject.toString();
    }

    @ResponseBody
    @GetMapping("tag")
    public String searchTagByWord(@RequestHeader("X-AUTH-TOKEN") String jwt, @RequestParam String word) throws Exception
    {
        JsonObject jsonObject = new JsonObject();

        if(jwt == null) { jsonObject.addProperty("code", StatusCode.NOT_FOUND); return jsonObject.toString(); }

        //Status Code
        jsonObject.addProperty("code", StatusCode.OK);

        //find registered another person's cards
        Set<Long> registeredUserCards = new HashSet<>();
        sharecardService.findAllByMemberId(jwtUtil.getAuthentication(jwt).get().getId()).forEach(sharecard -> {
            registeredUserCards.add(sharecard.getCard().getId());
        });

        //search keyword
        Set<Card> cards = new HashSet<>();
        tagService.findAllByWord(word).forEach(tag -> {
            cards.add(tag.getCard());
        });

        //AllUsers Info
        JsonArray userArray = new JsonArray();
        cards.forEach(card -> {
            if(registeredUserCards.contains(card.getId()))
            {
                JsonObject userObj = new JsonObject();

                //Tag Array
                JsonArray tagArray = new JsonArray();
                card.getTagList().forEach(tag -> {
                    tagArray.add(tag.getText());
                });

                userObj.addProperty("id", card.getId());
                userObj.addProperty("background", card.getBackground());
                userObj.addProperty("url", card.getUrl());
                userObj.addProperty("job", card.getJob());
                userObj.addProperty("phone", card.getPhoneNumber());
                userObj.addProperty("email", card.getEmail());
                userObj.addProperty("address", card.getAddress());
                userObj.addProperty("member_id", card.getMember().getId());
                userObj.add("tags", tagArray);

                userArray.add(userObj);
            }
        });

        jsonObject.add("users", userArray);

        return jsonObject.toString();
    }

}
