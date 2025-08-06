package start.querydsl_start.composite.noidentify.embed;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import start.querydsl_start.composite.noidentify.idclass.ParentV1;

@Entity
@Setter
@Getter
@ToString(of={"id","parentV2","name"})
public class ChildV2 {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="PARENT_ID1"),
            @JoinColumn(name="PARENT_ID2")
    })
    private ParentV2 parentV2;

    private String name;
}
