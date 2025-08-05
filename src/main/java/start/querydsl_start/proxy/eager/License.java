package start.querydsl_start.proxy.eager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import start.querydsl_start.proxy.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString(of ={"id","name"})
public class License {
    @Id
    @Column(name="LICENSE_ID")
    private Long id;

    private String name;


}
