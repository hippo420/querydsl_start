package start.querydsl_start.composite.identify.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of={"id","name"})
public class Flim {

    @Id
    @Column(name="FLIM_ID")
    private Long id;

    private String name;
}
