package start.querydsl_start.entity.advancedMapping.inherit.join;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("B")
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, of = {"author"})
public class Book extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="BOOK_ID")
    private Long id;

    private String author;

    public Book(String author) {
        this.author=author;
    }



}
