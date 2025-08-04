package start.querydsl_start.entity.advancedMapping.composite.identify.idclass;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailId implements Serializable {
    private Long order;
    private Long orderDetail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailId that)) return false;
        return Objects.equals(order, that.order) && Objects.equals(orderDetail, that.orderDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, orderDetail);
    }
}
