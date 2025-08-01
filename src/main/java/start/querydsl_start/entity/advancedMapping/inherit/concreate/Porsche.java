package start.querydsl_start.entity.advancedMapping.inherit.concreate;

import jakarta.persistence.*;
import lombok.*;
import start.querydsl_start.entity.advancedMapping.inherit.single.Phone;

@Entity
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name","price","f1"})
public class Porsche extends Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PRSCHE_ID")
    private Long id;
    private String name;
    private String price;
    private String f1;

    public Porsche(String name,String price, String f1)
    {
        this.name =name;
        this.price = price;
        this.f1 = f1;
    }
}
