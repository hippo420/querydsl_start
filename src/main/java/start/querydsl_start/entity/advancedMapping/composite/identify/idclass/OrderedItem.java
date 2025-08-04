package start.querydsl_start.entity.advancedMapping.composite.identify.idclass;

import jakarta.persistence.*;

@Entity
@IdClass(OrderItemId.class)
public class OrderedItem {
    @Id
    @ManyToOne
    @JoinColumns({@JoinColumn(name="ORDER_ID"),
                 @JoinColumn(name="ORDER_DETAIL_ID")})
    @GeneratedValue
    private OrderDetail orderDetail;

    @Id
    @Column(name="ORDER_ITEM_ID")
    private Long id;

    private String name;
}
