package start.querydsl_start.valuetype.embeded;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@ToString(of={"id","name","price","period"})
public class Festival {
    @Id
    @GeneratedValue
    private Long id;

    private String name;   //값타입
    private BigDecimal price;     //값타입

    @Embedded
    public Period period;
}
