package start.querydsl_start.composite.noidentify.idclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString(of={"id","parentV1","name"})
public class ChildV1 {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="PARENT_ID1"),
            @JoinColumn(name="PARENT_ID2")
    })
    private ParentV1 parentV1;

    private String name;
}
