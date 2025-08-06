package start.querydsl_start.inherit.single;

import jakarta.persistence.*;
import lombok.*;

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
