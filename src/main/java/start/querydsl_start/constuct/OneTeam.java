package start.querydsl_start.constuct;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Setter @Getter
@Slf4j
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})

public class OneTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    public OneTeam(String name) {
        System.out.println(this.getClass().getName()+" 의 커스텀생성자 입니다.");
        this.name = name;
    }

    public OneTeam() {
        System.out.println(this.getClass().getName()+" 의 기본생성자 입니다.");
    }
}
