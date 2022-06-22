package kr.curious.Bzip.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"cardList"})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime unregisteredAt;

    // Member 1 : N Card
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Card> cardList;

}
