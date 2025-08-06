package start.querydsl_start.composite.identify.idclass;

import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@ToString(of={"",""})
public class ActorId implements Serializable {
    private Long movie;
    private Long actorId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ActorId actorId1 = (ActorId) o;
        return Objects.equals(movie, actorId1.movie) && Objects.equals(actorId, actorId1.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, actorId);
    }
    public ActorId(Long movie, Long actorId) {
        this.movie = movie;
        this.actorId = actorId;
    }

    public ActorId(){}
}
