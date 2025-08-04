package start.querydsl_start.entity.advancedMapping.composite.identify.idclass;

import jakarta.persistence.*;

@Entity
@IdClass(OrderDatailId.class)
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    @GeneratedValue
    private Order order;

    private String name;
}
