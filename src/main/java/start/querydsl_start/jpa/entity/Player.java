package start.querydsl_start.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of={"id","name","age","type","salary","team"})

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String type;
    private Long salary;
    private String team;

    public Player() {}
    public Player(String name, Integer age, String type, Long salary, String team) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.salary = salary;
        this.team = team;
    }
}
