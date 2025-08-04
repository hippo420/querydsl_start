package start.querydsl_start.entity.advancedMapping.composite.noidentify.idclass;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString( of = {"id","name"})
@IdClass(UserId.class)
public class User {

    //UseId.id1과 연결
    @Id
    @Column(name="USER_ID1")
    private Long id1;

    //UseId.id2과 연결
    @Id
    @Column(name="USER_ID2")
    private Long id2;

    private String name;


}
