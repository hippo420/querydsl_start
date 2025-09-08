package start.querydsl_start.query.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter //프로퍼티방식
@ToString(of={"username","age","firmName"})
public class TraderDTO {
    private String username;
    private int age;
    private String firmName;
    public TraderDTO(){}

    //생성자 방식
    @QueryProjection
    public TraderDTO(String username, int age) {
        this.username = username;
        this.age = age;
    }

}
