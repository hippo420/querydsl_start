package start.querydsl_start.entity.advancedMapping.composite.noidentify.idclass;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString( of = {"id","user"})
public class UserAddress {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="USER_ID1",referencedColumnName = "USER_ID1"),
            //referencedColumnName 와 name이 같으면 생략가능
            @JoinColumn(name="USER_ID2")
    })
    private User user;

}
