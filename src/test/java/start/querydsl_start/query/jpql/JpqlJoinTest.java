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
import start.querydsl_start.query.entity.Firm;
import start.querydsl_start.query.entity.Trader;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JpqlJoinTest {


    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void initData() {
        Firm samsung = new Firm("삼성증권");
        Firm mirae   = new Firm("미래에셋");
        em.persist(samsung);
        em.persist(mirae);

        Trader t1 = new Trader("kim", 30, samsung, new BigDecimal("3000"));
        Trader t2 = new Trader("lee", 40, samsung, new BigDecimal("4000"));
        Trader t3 = new Trader("park", 25, mirae, new BigDecimal("3500"));
        Trader t4 = new Trader("choi", 28, mirae, new BigDecimal("3000"));
        Trader t5 = new Trader("jung", 35, samsung, new BigDecimal("2000"));

        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.persist(t4);
        em.persist(t5);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("JPQL-[내부조인] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_InnerJoin() {
        String jpql = "select t from Trader t join t.firm f " +
                "where f.name = :firmName";

        List<Trader> result = em.createQuery(jpql, Trader.class)
                .setParameter("firmName", "삼성증권")
                .getResultList();

        result.forEach(t -> log.info("{}", t));
    }

    @Test
    @DisplayName("JPQL-[외부조인] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_OuterJoin() {
        String jpql = "select f.name, t.username " +
                "from Firm f left join f.traders t";

        List<Object[]> result = em.createQuery(jpql, Object[].class)
                .getResultList();

        for (Object[] row : result) {
            log.info("Firm={}, Trader={}", row[0], row[1]);
        }
    }

    @Test
    @DisplayName("JPQL-[세타조인] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_ThetaJoin() {
        String jpql = "select t, f from Trader t, Firm f " +
                "where t.username = f.name";  // 단순 조건 매칭

        List<Object[]> result = em.createQuery(jpql, Object[].class)
                .getResultList();

        for (Object[] row : result) {
            Trader trader = (Trader) row[0];
            Firm firm = (Firm) row[1];
            log.info("ThetaJoin => Trader={}, Firm={}", trader.getUsername(), firm.getName());
        }
    }

    @Test
    @DisplayName("JPQL-[페치조인] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_FetchJoin() {
        String jpql = "select t from Trader t join fetch t.firm";

        List<Trader> result = em.createQuery(jpql, Trader.class)
                .getResultList();

        for (Trader t : result) {
            log.info("Trader={}, Firm={}", t.getUsername(), t.getFirm().getName());
        }
    }

    @Test
    @DisplayName("JPQL-[컬렉션조인] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_CollectionJoin() {
        String jpql = "select f.name, t.username " +
                "from Firm f join f.traders t";

        List<Object[]> result = em.createQuery(jpql, Object[].class)
                .getResultList();

        for (Object[] row : result) {
            log.info("Firm={}, Trader={}", row[0], row[1]);
        }
    }


}
