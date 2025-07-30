package start.querydsl_start.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PRODUCT_ID")
    private Long id;

    @Column(name="product_name")
    private String name;

    //양방향 설정
    @ManyToMany(mappedBy = "products")
    private List<Member> members;


    public Product(String name) {
        this.name = name;
    }

}
