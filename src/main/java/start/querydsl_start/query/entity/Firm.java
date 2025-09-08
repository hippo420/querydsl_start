package start.querydsl_start.query.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy="firm")
    @JsonManagedReference
    private List<Trader> traders = new ArrayList<>();

    public Firm() {}
    public Firm(String name) {
        this.name = name;
    }

    public void addTrader(Trader trader) {
        traders.add(trader);
        trader.setFirm(this); // 양방향 편의 메서드
    }
}
