package start.querydsl_start.entity.advancedMapping.inherit.superclass;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
//매핑정보 재정의
@AttributeOverride(name="id", column = @Column(name="SELLER_ID"))
//연관관계 재정의
//@AssociationOverride()
@ToString(callSuper = true, of = {"certification"})
public class Seller extends BaseEntity{
    //id, name, company상속
    private String certification;
}
