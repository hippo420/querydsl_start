package start.querydsl_start.entity.advancedMapping.composite.noidentify;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString( of = {"id","user","city"})
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;


}
