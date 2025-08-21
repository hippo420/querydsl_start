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
import start.querydsl_start.query.entity.dto.FirmSalaryStatsDTO;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JpqlAggregateTest {


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
    @DisplayName("JPQL-[그룹 함수 DTO] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_GroupFunction() {
        // JPQL (Firm별 salary 평균, 합계, 최댓값, 최솟값, 인원수)
        String jpql = "select t.firm.name, " +
                "       count(t), " +
                "       sum(t.salary), " +
                "       avg(t.salary), " +
                "       max(t.salary), " +
                "       min(t.salary) " +
                "from Trader t " +
                "group by t.firm.name " +
                "order by avg(t.salary) desc";

        List<Object[]> resultList = em.createQuery(jpql, Object[].class)
                .getResultList();

        for (Object[] row : resultList) {
            String firmName   = (String) row[0];
            Long traderCount  = (Long) row[1];
            BigDecimal total  = (BigDecimal) row[2];
            Double average    = (Double) row[3];
            BigDecimal max    = (BigDecimal) row[4];
            BigDecimal min    = (BigDecimal) row[5];

            log.info("증권사(firm) : {}, 인원수(count) : {}명, 총 급여(sum) : {}원, 평균급여(avg) : {}원, 최대급여(max) : {}원, 최소급여(min)={}원",
                    firmName, traderCount, total, average, max, min);
        }
    }

    @Test
    @DisplayName("JPQL-[그룹 함수 DTO 매핑] 테스트")
    @Transactional
    @Rollback(false)
    void testJPQL_GroupFunctionWithDTO() {
        String jpql = "select new start.querydsl_start.query.entity.dto.FirmSalaryStatsDTO(" +
                "       t.firm.name, " +
                "       count(t), " +
                "       sum(t.salary), " +
                "       avg(t.salary), " +
                "       max(t.salary), " +
                "       min(t.salary)) " +
                "from Trader t " +
                "group by t.firm.name " +
                "order by avg(t.salary) desc";

        List<FirmSalaryStatsDTO> resultList = em.createQuery(jpql, FirmSalaryStatsDTO.class)
                .getResultList();

        resultList.forEach(stats -> log.info("{}", stats));
    }


}
