package start.querydsl_start.valuetype.basic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Basic {
    @Id
    @GeneratedValue
    private Long id;

    private String name;   //값타입
    private int age;       //값타입

}
