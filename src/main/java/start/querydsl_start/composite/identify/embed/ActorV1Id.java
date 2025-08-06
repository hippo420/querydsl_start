package start.querydsl_start.composite.identify.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import start.querydsl_start.composite.identify.idclass.ActorId;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ActorV1Id implements Serializable {
    private Long actorId;

    @Column(name = "ACTOR_ID")
    private Long id;

    public ActorV1Id() {}

    public ActorV1Id(Long actorId,Long id) {
        this.actorId = actorId;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ActorV1Id actorV1Id = (ActorV1Id) o;
        return Objects.equals(actorId, actorV1Id.actorId) && Objects.equals(id, actorV1Id.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, id);
    }
}
