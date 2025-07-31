package start.querydsl_start.entity;

import java.io.Serializable;
import java.util.Objects;

public class MemberProductId implements Serializable {

    private String member;
    private String product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberProductId that)) return false;
        return Objects.equals(member, that.member) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, product);
    }
}
