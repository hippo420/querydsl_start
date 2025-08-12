package start.querydsl_start.realation.onetomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of = {"id","name","post"})
public class AttachmentV1 {
    @Id
    @GeneratedValue
    @Column(name = "ATTACHMENT_ID")
    private Long id;

    private String name;

    //같은 외래키로 ManyToOne 읽기전용
    @ManyToOne
    @JoinColumn(name = "POST_ID", insertable = false, updatable = false)
    private PostV1 post;
}
