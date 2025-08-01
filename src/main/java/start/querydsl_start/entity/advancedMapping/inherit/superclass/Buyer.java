package start.querydsl_start.entity.advancedMapping.inherit.superclass;

import jakarta.persistence.Entity;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Setter
@ToString(callSuper = true, of = {"account"})
public class Buyer extends BaseEntity {
    //id, name, company상속
    private BigDecimal account;
}
