package start.querydsl_start.entity.advancedMapping.inherit.mapping;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
//매핑정보 재정의
@AttributeOverride(name="id", column = @Column(name="SELLER_ID"))
//연관관계 재정의
//@AssociationOverride()
public class Seller extends BaseEntity{
    //id, name, company상속
    private String certification;
}
