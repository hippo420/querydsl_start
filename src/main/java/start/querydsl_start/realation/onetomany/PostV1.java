package start.querydsl_start.realation.onetomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@ToString(of = {"id","name"})
public class PostV1 {
    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="POST_ID")
    private List<AttachmentV1> attachments = new ArrayList<AttachmentV1>();
}
