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
    @JoinColumn(name="MOVIE_ID")
    private Movie movie;

    @Id
    @Column(name="ACTOR_ID")
    private Long actorId;

    private String name;
}
