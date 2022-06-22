package kr.curious.Bzip.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"member", "tagList", "shareCardList"})
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String background;

    private String url;

    private String name;

    private String job;

    private String email;

    //Card N : 1 Member
    @ManyToOne
    private Member member;

    //Card 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    private List<Tag> tagList;

    //Card 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    private List<Sharecard> shareCardList;

}
