package kr.curious.Bzip.model.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@ToString(exclude = {"member", "tagList", "shareCardList"})
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String background;

    @Nullable
    private String url;

    @Nullable
    private String name;

    @Nullable
    private String job;

    @Nullable
    private String phoneNumber;

    @Nullable
    private String address;

    @Nullable
    private String email;

    //Card N : 1 Member
    @ManyToOne
    private Member member;

    //Card 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    private List<Tag> tagList;

    //Card 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    private List<Sharecard> shareCardList; //타인의 카드 리스트

}
