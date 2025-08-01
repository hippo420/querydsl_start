package start.querydsl_start.entity.advancedMapping.inherit.mapping;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Buyer extends BaseEntity {
    //id, name, company상속
    private BigDecimal account;
}
