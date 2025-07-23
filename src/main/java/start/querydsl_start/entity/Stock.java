package start.querydsl_start.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import start.querydsl_start.constant.StockType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "item"})
@Table(name="stock",uniqueConstraints = {@UniqueConstraint(
        name = "ITEM_NAME_UNIQUE",
        columnNames = {"ITEM", "NAME"}
)})
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item", unique = true, nullable = false,length = 12)
    private String item;

    @Column(name = "name", nullable = false,length = 200)
    private String name;

    //precision=정수부+소수부,scale=소수부
    @Column(name ="price",nullable = false, scale = 2, precision = 10)
    private BigDecimal price;

    @Column(name ="qty", length = 10, nullable = false)
    private Long qty;

    @Column(name ="type")
    @Enumerated(EnumType.STRING)
    private StockType type;

    @Temporal(TemporalType.DATE)
    @Column(name="ipo_date")
    private Date ipoDate;

    @Lob
    @Column(name="remark")
    private String remark;

    @Transient
    private String data1;


}
