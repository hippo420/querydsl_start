package start.querydsl_start.query.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
import start.querydsl_start.jpa.entity.QPlayer;
import start.querydsl_start.query.entity.Firm;
import start.querydsl_start.query.entity.QTrader;
import start.querydsl_start.query.entity.Trader;

import java.math.BigDecimal;
import java.util.List;

import static start.querydsl_start.query.entity.QFirm.firm;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueryDSLAggregationTest {


    @PersistenceContext
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void initData() {
        // 테스트 클래스 실행 전에 딱 한 번 실행됨
        System.out.println("before all");
        Player p1 = new Player("손흥민",33,"축구",10000L,"la Galuxy Football");
        Player p2 = new Player("오나티 쇼헤이",24,"야구",30000L,"LA 다저스");
        Player p21 = new Player("김혜성",25,"야구",2000L,"LA 다저스");
        Player p3 = new Player("스테판 커리",30,"농구",5000L,"LA 레이커스");
        Player p4 = new Player("막스 페르스타펀",28,"F1 레이싱",2000L,"F1 레드불 레이싱");

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

        em.persist(p1);
        em.persist(p2);
        em.persist(p21);
        em.persist(p3);
        em.persist(p4);
        em.flush();
        em.clear();

        jpaQueryFactory = new JPAQueryFactory(em);
    }


    @Test
    @DisplayName("QueryDSL-[집합함수] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_Aggregation()
    {
        QTrader t = QTrader.trader;

        List<Tuple> result = jpaQueryFactory
                .select(t.count(),
                        t.salary.sum(),
                        t.salary.avg(),
                        t.salary.min(),
                        t.salary.max()
                )
                .from(t)
                .fetch();

        Tuple tuple = result.get(0);
        log.info("총: {}개",tuple.get(t.count()));
        log.info("총급여 합계: {}원",tuple.get(t.salary.sum()));
        log.info("평균 급여액: {}원",tuple.get(t.salary.avg()));
        log.info("최소 급여액: {}원",tuple.get(t.salary.min()));
        log.info("최대 급여액: {}원",tuple.get(t.salary.max()));

    }

    @Test
    @DisplayName("QueryDSL-[집합함수-groupBy] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_Aggregation1()
    {
        QTrader t = QTrader.trader;

        List<Tuple> result = jpaQueryFactory
                .select(firm.name,
                        t.count(),
                        t.salary.sum(),
                        t.salary.avg(),
                        t.salary.min(),
                        t.salary.max()
                )
                .from(t)
                .join(t.firm, firm)
                .groupBy(firm.name)
                .fetch();

        for(int i =0; i<result.size();i++)
        {
            Tuple tuple = result.get(i);

            log.info("회사 : [{}] - 총: {}개",tuple.get(firm.name),tuple.get(t.count()));
            log.info("총급여 합계: {}원",tuple.get(t.salary.sum()));
            log.info("평균 급여액: {}원",tuple.get(t.salary.avg()));
            log.info("최소 급여액: {}원",tuple.get(t.salary.min()));
            log.info("최대 급여액: {}원",tuple.get(t.salary.max()));
        }


    }

    @Test
    @DisplayName("QueryDSL-[집합함수-having] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_Aggregation2()
    {
        QTrader t = QTrader.trader;

        List<Tuple> result = jpaQueryFactory
                .select(firm.name,
                        t.count(),
                        t.salary.sum(),
                        t.salary.avg(),
                        t.salary.min(),
                        t.salary.max()
                )
                .from(t)
                .join(t.firm, firm)
                .groupBy(firm.name)
                .having(firm.name.startsWithIgnoreCase("Goldman")
                        .or(firm.name.startsWithIgnoreCase("JP")))
                .fetch();

        for(int i =0; i<result.size();i++)
        {
            Tuple tuple = result.get(i);

            log.info("회사 : [{}] - 총: {}개",tuple.get(firm.name),tuple.get(t.count()));
            log.info("총급여 합계: {}원",tuple.get(t.salary.sum()));
            log.info("평균 급여액: {}원",tuple.get(t.salary.avg()));
            log.info("최소 급여액: {}원",tuple.get(t.salary.min()));
            log.info("최대 급여액: {}원",tuple.get(t.salary.max()));
        }


    }

}
