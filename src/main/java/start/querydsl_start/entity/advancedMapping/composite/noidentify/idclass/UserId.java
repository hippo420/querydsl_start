package start.querydsl_start.entity.advancedMapping.composite.noidentify.idclass;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

public class UserId implements Serializable {

    //User.id1랑 매핑
    private Long id1;

    //User.id2랑 매핑
    private Long id2;

    public UserId() {}
    public UserId(Long id1, Long id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(id1, userId.id1) && Objects.equals(id2, userId.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }
}
