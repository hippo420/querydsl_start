package start.querydsl_start.valuetype;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.composite.noidentify.embed.ChildV2;
import start.querydsl_start.composite.noidentify.embed.ParentV2;
import start.querydsl_start.composite.noidentify.embed.ParentV2Id;
import start.querydsl_start.composite.noidentify.idclass.ChildV1;
import start.querydsl_start.composite.noidentify.idclass.ParentV1;
import start.querydsl_start.composite.noidentify.idclass.ParentV1Id;
import start.querydsl_start.valuetype.embeded.Festival;
import start.querydsl_start.valuetype.embeded.Period;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
public class ValueTypeTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("값타입 공유 - [Embeded] 테스트")
    @Transactional
    @Rollback(false)
    void testValueType_Embeded()
    {
        log.info("값복사 공유발생");
        Period p1 = new Period("20250701","20250801");

        Festival f1 = new Festival();
        f1.setName("남자월드컵");
        f1.setPrice(BigDecimal.valueOf(150000));
        f1.setPeriod(p1);
        em.persist(f1);

        Period p2 = f1.getPeriod();
        Festival f2 = new Festival();
        f2.setName("여자월드컵");
        f2.setPrice(BigDecimal.valueOf(150000));
        p2.setStartDate("20230101");
        f2.setPeriod(p2);
        em.persist(f2);

        em.flush();
        em.clear();
        List<Festival> res1 = em.createQuery("select m from Festival m", Festival.class).getResultList();
        res1.forEach( r -> log.info("{}",r));
    }

    @Test
    @DisplayName("값타입 공유해결- [Embeded-clone()사용] 테스트")
    @Transactional
    @Rollback(false)
    void testValueType_EmbededV1() throws CloneNotSupportedException {
        log.info("값복사 공유문제 해결 - clone()사용");
        Period p1 = new Period("20250701","20250801");


        Festival f1 = new Festival();
        f1.setName("남자월드컵");
        f1.setPrice(BigDecimal.valueOf(150000));
        f1.setPeriod(p1);
        em.persist(f1);

        Period p2 = (Period) f1.getPeriod().clone();
        Festival f2 = new Festival();
        f2.setName("여자월드컵");
        f2.setPrice(BigDecimal.valueOf(150000));
        p2.setStartDate("20230101");
        f2.setPeriod(p2);
        em.persist(f2);

        em.flush();
        em.clear();
        List<Festival> res1 = em.createQuery("select m from Festival m", Festival.class).getResultList();
        res1.forEach( r -> log.info("{}",r));


    }




}
