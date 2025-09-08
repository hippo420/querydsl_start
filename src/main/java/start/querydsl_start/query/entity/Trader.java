package start.querydsl_start.query.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Entity
@Getter @Setter
@ToString(of={"id","username","age","salary"})
public class Trader {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIRM_ID")
    //@JsonIgnore
    @JsonBackReference
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
