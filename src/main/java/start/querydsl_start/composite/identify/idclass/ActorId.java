package start.querydsl_start.composite.identify.idclass;

import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@ToString(of={"flim","actorId"})
public class ActorId implements Serializable {
    private Long flim;
    private Long actorId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ActorId actorId1 = (ActorId) o;
        return Objects.equals(flim, actorId1.flim) && Objects.equals(actorId, actorId1.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flim, actorId);
    }
    public ActorId(Long movie, Long actorId) {
        this.flim = movie;
        this.actorId = actorId;
    }

    public ActorId(){}
}
