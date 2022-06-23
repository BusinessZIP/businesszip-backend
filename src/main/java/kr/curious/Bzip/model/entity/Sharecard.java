package kr.curious.Bzip.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sharecard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 PK

    private Long memberId; // 사용자 memberId

    //Sharecard N : 1 Card
    @ManyToOne
    private Card card; // 사용자가 가지고 있는 card Id

}
