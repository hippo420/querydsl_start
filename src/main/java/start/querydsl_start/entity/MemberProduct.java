package start.querydsl_start.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@IdClass(MemberProductId.class)
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProduct {

    @Id
    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    private String orderDate;
}
