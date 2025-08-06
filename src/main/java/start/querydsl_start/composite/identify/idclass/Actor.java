package start.querydsl_start.composite.identify.idclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@IdClass(ActorId.class)
@ToString(of={"id","name"})
public class Actor {

    @Id
    @ManyToOne
    @JoinColumn(name="FLIM_ID")
    private Flim flim;

    @Id
    @Column(name="ACTOR_ID")
    private Long actorId;

    private String name;
}
