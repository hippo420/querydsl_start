package start.querydsl_start.proxy.lazy;

import jakarta.persistence.Column;
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
@ToString(of ={"id","name"})
public class InvoiceLine {
    @Id
    @GeneratedValue
    @Column(name="INVOICE_LINE_ID")
    private Long id;

    private String name;

    private Long qty;
}
