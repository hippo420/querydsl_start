package start.querydsl_start.entity.advancedMapping.inherit.single;

import jakarta.persistence.*;
import lombok.*;
import start.querydsl_start.entity.advancedMapping.inherit.join.Item;

@Entity
@DiscriminatorValue("Samsung")
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, of = {"kPassCard"})
public class Galaxy extends Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GALAXY_ID")
    private Long id;

    private String kPassCard;

    public Galaxy(String kPassCard) {
        this.kPassCard=kPassCard;
    }



}
