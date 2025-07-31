package start.querydsl_start.entity.advancedMapping.inherit.single;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Setter @Getter
@ToString(of = {"id", "name","price"})
public abstract class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PHONE_ID")
    private Long id;

    private String name;
    private String price;

}


