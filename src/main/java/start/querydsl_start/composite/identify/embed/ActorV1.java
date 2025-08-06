package start.querydsl_start.composite.identify.embed;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import start.querydsl_start.composite.identify.idclass.Flim;

@Entity
@Getter @Setter
@ToString(of={"id","movieV1","name"})
public class ActorV1 {

    @EmbeddedId
    private ActorV1Id id;

    @MapsId("actorId")     //ActorV1Id.actorId매핑
    @ManyToOne
    @JoinColumn(name="MOVIE_ID")
    private MovieV1 movieV1;

    private String name;
}
