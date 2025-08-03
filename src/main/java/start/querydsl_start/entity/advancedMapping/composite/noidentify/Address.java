package start.querydsl_start.entity.advancedMapping.composite.noidentify;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString( of = {"id","user","city"})
public class Address {
    @Id
    @GeneratedValue
    private Long id;


}
