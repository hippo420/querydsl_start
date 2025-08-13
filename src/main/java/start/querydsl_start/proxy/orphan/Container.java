package start.querydsl_start.proxy.orphan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Container {
    @Id
    @GeneratedValue
    @Column(name="CONTAINER_ID")
    private Long id;

    private String name;
}
