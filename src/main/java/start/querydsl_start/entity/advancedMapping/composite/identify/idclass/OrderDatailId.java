package start.querydsl_start.entity.advancedMapping.composite.identify.idclass;

import java.io.Serializable;
import java.util.Objects;

public class OrderDatailId implements Serializable {
    private Long order;
    private Long orderDatil;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDatailId that)) return false;
        return Objects.equals(order, that.order) && Objects.equals(orderDatil, that.orderDatil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, orderDatil);
    }
}
