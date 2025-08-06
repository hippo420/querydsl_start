package start.querydsl_start.realationMapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
public class OneToManyTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("다대일 - 단방향매핑 테스트")
    @Transactional
    @Rollback(false)
    void testManyToOne()
    {


        em.persist(null);

        em.flush();
        em.clear();



        //조회
        List<Object> res1 = em.createQuery("select m from Stock m", Object.class)
                .getResultList();


        res1.stream().forEach(r -> System.out.println(r));
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
