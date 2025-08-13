package start.querydsl_start.proxy.orphan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Docker {
    @Id
    @GeneratedValue
    @Column(name="DOCKER_ID")
    private Long id;

    private String name;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="CONTAINER_ID")
    private List<Container> contaners = new ArrayList<>();
}
