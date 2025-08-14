package start.querydsl_start.proxy.orphan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString(of={"id","name","containers"})
public class Docker {
    @Id
    @GeneratedValue
    @Column(name="DOCKER_ID")
    private Long id;

    private String name;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="DOCKER_ID")
    private List<Container> containers = new ArrayList<>();
}
