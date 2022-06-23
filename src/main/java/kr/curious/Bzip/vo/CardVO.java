package kr.curious.Bzip.vo;

import kr.curious.Bzip.model.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CardVO {
    //private Long id;
    private String name;
    private String job;
    private String background;
    private String url;
    private String email;
    private String phone;
    private String Address;
    private List<String> tags;
}
