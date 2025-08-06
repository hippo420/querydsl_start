package start.querydsl_start.inherit.concreate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString( of = {"id","name","price"})
public class Kia{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="KIA_ID")
    private Long id;
    private String name;
    private String price;

    public Kia(String name, String price) {
        this.name = name;
        this.price = price;
    }
}
