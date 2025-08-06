package start.querydsl_start.composite.identify.idclass;

import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;
@ToString(of={"Actor","id"})
public class PersonId implements Serializable {

    private ActorId Actor; //Person.actor매핑
    private Long id;       //Person.id매핑

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonId personId = (PersonId) o;
        return Objects.equals(Actor, personId.Actor) && Objects.equals(id, personId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Actor, id);
    }


    public PersonId(){}

    public PersonId(ActorId Actor, Long id) {
        this.Actor = Actor;
        this.id = id;
    }

}
