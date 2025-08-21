package start.querydsl_start.query.entity.dto;

import lombok.ToString;



@ToString(of={"username","age"})
public class TraderDTO {
    private String username;
    private int age;

    public TraderDTO(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
