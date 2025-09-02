package start.querydsl_start.query.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Entity
@Getter @Setter
@ToString(of={"id","username","age","firm","salary"})
public class Trader {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIRM_ID")
    private Firm firm;

    private BigDecimal salary;

    public Trader() {}

    public Trader(String username, int age, Firm firm, BigDecimal salary) {
        this.username = username;
        this.age = age;
        this.firm = firm;
        this.salary = salary;
    }
}
