package start.querydsl_start.jpa.named;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(of={"id","name","age"})
@NamedQueries({
        @NamedQuery(name="Named.findByName", query="select m from Named m where m.name = :name")
})
public class Named {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer age;

    public Named(){}

    public Named(String name, int age)
    {
        this.name = name;
        this.age=age;
    }
}
