package start.querydsl_start.query.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import start.querydsl_start.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of={"id","name"})
public class Firm {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy="")
    @JoinColumn(name="TRADER_ID")
    private List<Trader> traders = new ArrayList<>();

}
