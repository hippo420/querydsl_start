package start.querydsl_start.entity.advancedMapping.inherit.single;

import jakarta.persistence.*;
import lombok.*;
import start.querydsl_start.entity.advancedMapping.inherit.join.Item;

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
