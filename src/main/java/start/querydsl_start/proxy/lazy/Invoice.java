package start.querydsl_start.proxy.lazy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(of ={"id","name"})
public class Invoice {
    @Id
    @GeneratedValue
    @Column(name = "INVOICE_ID")
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVOICE_LINE_ID")
    private InvoiceLine invoiceLine;

}
