package start.querydsl_start.realationMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.realation.manytoone.Student;
import start.querydsl_start.realation.manytoone.Subject;

import java.util.List;
@Slf4j
@SpringBootTest
public class ManyToOneTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("다대일 - 단방향매핑 테스트")
    @Transactional
    @Rollback(false)
    void testManyToOne()
    {

        Subject s1 = new Subject();
        s1.setName("과학");
        Subject s2 = new Subject();
        s2.setName("수학");
        em.persist(s1);
        em.persist(s2);

        Student st1 = new Student();
        st1.setName("김상호");
        st1.setSubject(s1);
        Student st2 = new Student();
        st2.setName("노재윤");
        st2.setSubject(s2);
        Student st3 = new Student();
        st3.setName("강지수");
        st3.setSubject(s2);
        em.persist(st1);
        em.persist(st2);
        em.persist(st3);

        em.flush();
        em.clear();


        //조회
        em.createQuery("select m from Subject m", Subject.class)
                .getResultList().forEach(r -> log.info("RESULT1 = {}",r));
        em.createQuery("select m from Student m", Student.class)
                .getResultList().forEach(r -> log.info("RESULT2 = {}",r));


    }


    @Test
    @DisplayName("다대일 - 양방향매핑 테스트")
    @Transactional
    @Rollback(false)
    void testManyToOne_OneToMany()
    {


        em.persist(null);

        em.flush();
        em.clear();



        //조회
        List<Object> res1 = em.createQuery("select m from Stock m", Object.class)
                .getResultList();


        res1.stream().forEach(r -> System.out.println(r));
    }

}
