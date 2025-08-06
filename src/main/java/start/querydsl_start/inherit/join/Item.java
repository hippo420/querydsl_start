package start.querydsl_start.inherit.join;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@Setter @Getter
@ToString(of = {"id", "name","price"})
public abstract class  Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ITEM_ID")
    private Long id;

    private String name;
    private String price;

}


