package start.querydsl_start.query.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString(of={"id","name"})
public class TradeOrder {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal quantity;
}
