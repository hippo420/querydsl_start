package start.querydsl_start.realation.onetomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@ToString(of = {"id","name","posts"})
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name="POST_ID")
    private List<Attachment> attachments = new ArrayList<Attachment>();

}
