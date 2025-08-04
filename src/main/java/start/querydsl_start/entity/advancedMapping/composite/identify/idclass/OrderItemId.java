package start.querydsl_start.entity.advancedMapping.composite.identify.idclass;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemId implements Serializable {
    private Long orderDatil;
    private Long orderItem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemId that)) return false;
        return Objects.equals(orderDatil, that.orderDatil) && Objects.equals(orderItem, that.orderItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDatil, orderItem);
    }
}
