package start.querydsl_start.composite.noidentify.embed;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString(of={"id","name"})
public class ParentV2 {
    @EmbeddedId
    private ParentV2Id id;

    private String name;
}
