package start.querydsl_start.proxy.eager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString(of ={"id","name"})
public class Driver {
    @Id
    @Column(name="DRIVER_ID")
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="LICENSE_ID")
    private License license;
}
