package start.querydsl_start.query.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(of={"id","name"})
public class ItemStock {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private long quantity;


}
