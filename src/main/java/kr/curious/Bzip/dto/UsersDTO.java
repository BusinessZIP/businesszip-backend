package kr.curious.Bzip.dto;

import kr.curious.Bzip.model.entity.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UsersDTO {
    private int statusCode;
    private List<Card> cardList;
}
