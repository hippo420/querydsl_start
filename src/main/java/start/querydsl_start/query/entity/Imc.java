package start.querydsl_start.query.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@ToString(of={"id","name","fund"})
public class Imc {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
