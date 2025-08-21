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

    @OneToMany(mappedBy="firm")
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
