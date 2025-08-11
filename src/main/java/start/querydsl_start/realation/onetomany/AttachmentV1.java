package start.querydsl_start.realation.onetomany;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of = {"id","name"})
public class AttachmentV1 {
    @Id
    @GeneratedValue
    @Column(name = "ATTACHMENT_ID")
    private Long id;

    private String name;

}
