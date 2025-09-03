package start.querydsl_start.query.entity.dto;

import lombok.Setter;
import lombok.ToString;


@Setter //프로퍼티방식
@ToString(of={"name","age"})
public class TraderDTOV1 {
    private String name;
    private int age;

    public TraderDTOV1(){}

    //생성자 방식
    public TraderDTOV1(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
