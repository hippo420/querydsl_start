package start.querydsl_start.query.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Setter;
import lombok.ToString;


@Setter //프로퍼티방식
@ToString(of={"username","age"})
public class TraderDTO {
    private String username;
    private int age;

    public TraderDTO(){}

    //생성자 방식
    @QueryProjection
    public TraderDTO(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
