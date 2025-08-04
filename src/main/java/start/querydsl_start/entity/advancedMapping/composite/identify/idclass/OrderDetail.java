package start.querydsl_start.entity.advancedMapping.composite.identify.idclass;

import jakarta.persistence.*;

@Entity
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @Id @Column(name="ORDER_DETAIL_ID")
    private Long orderDetailId;

    private String name;
}
