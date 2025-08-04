package start.querydsl_start.entity.advancedMapping.composite.noidentify.embed;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString( of = {"id","title"})
public class Board {

    @Id
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(name="TITLE")
    private String title;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();
}
