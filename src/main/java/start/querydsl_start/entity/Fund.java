package start.querydsl_start.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TableGenerator(
        name="TABLE_SEQ_GENERATOR",
        table = "SEQ_TBL",
        pkColumnName ="SEQ_TYCD",
        pkColumnValue = "FUND_SEQ",
        allocationSize = 1
)
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "TABLE_SEQ_GENERATOR")
    @Column(name="FUND_ID")
    private Long id;

    private String code;

    private String name;

    public Fund(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
