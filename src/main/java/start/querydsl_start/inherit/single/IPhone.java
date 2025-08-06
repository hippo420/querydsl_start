package start.querydsl_start.inherit.single;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("Apple")
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, of = {"tMoney"})
public class IPhone extends Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IPHONE_ID")
    private Long id;

    private String tMoney;

    public IPhone(String tMoney) {
        this.tMoney=tMoney;
    }
}
