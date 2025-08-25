package start.querydsl_start.query.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(of={"tradername","acct"})
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String tradername;

    private String acct;

    public Account() {}

    public Account(String tradername,String acct) {
        this.tradername = tradername;
        this.acct = acct;
    }
}