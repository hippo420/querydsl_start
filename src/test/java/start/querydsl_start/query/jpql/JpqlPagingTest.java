package start.querydsl_start.query.jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import start.querydsl_start.jpa.entity.Player;
import start.querydsl_start.query.entity.Firm;
import start.querydsl_start.query.entity.Trader;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JpqlPagingTest {


    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void initData() {
        Firm f1 = new Firm("Goldman Sachs");
        Firm f2 = new Firm("Morgan Stanley");
        Firm f3 = new Firm("JP Morgan");
        em.persist(f1);
        em.persist(f2);
        em.persist(f3);

        List<Trader> traders = List.of(
                new Trader("alice", 25, f1, BigDecimal.valueOf(5000)),
                new Trader("bob", 30, f2, BigDecimal.valueOf(7000)),
                new Trader("charlie", 28, f3, BigDecimal.valueOf(6500)),
                new Trader("david", 35, f1, BigDecimal.valueOf(9000)),
                new Trader("eva", 22, f2, BigDecimal.valueOf(4000)),
                new Trader("frank", 40, f3, BigDecimal.valueOf(12000)),
                new Trader("grace", 27, f1, BigDecimal.valueOf(5800)),
                new Trader("henry", 32, f2, BigDecimal.valueOf(7500)),
                new Trader("irene", 29, f3, BigDecimal.valueOf(6800)),
                new Trader("jack", 31, f1, BigDecimal.valueOf(8100))
        );

        traders.forEach(em::persist);
        em.flush();
        em.clear();
    }


    @Test
    @DisplayName("JPQL-[페이징] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_V1()
    {
        //WHERE
        String sql = "select m from Trader m order by m.salary ASC";

        List<Trader> traders = em.createQuery(sql,Trader.class)
                .setFirstResult(0)
                .setMaxResults(5)
                .getResultList();
        traders.forEach(p -> log.info("{}",p));

    }


}
