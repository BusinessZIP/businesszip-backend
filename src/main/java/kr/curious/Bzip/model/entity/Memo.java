package kr.curious.Bzip.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 PK

    private Long memberId; // 작성자 memberId

    private String content;

    //Sharecard N : 1 Card
    @ManyToOne
    private Card card; // card Id

}
