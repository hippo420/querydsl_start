package start.querydsl_start.inherit.join;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("M")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, of = {"director"})
public class Movie extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MOVIE_ID")
    private Long id;

    private String director;

    public Movie(String director) {
        this.director=director;
    }
}
