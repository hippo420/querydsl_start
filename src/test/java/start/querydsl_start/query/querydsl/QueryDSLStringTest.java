package start.querydsl_start.query.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
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
import start.querydsl_start.query.entity.Firm;
import start.querydsl_start.query.entity.QTrader;
import start.querydsl_start.query.entity.Trader;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueryDSLStringTest {


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
                new Trader("jack", 31, f1, BigDecimal.valueOf(8100)),
                new Trader("jackson", 22, f1, null),
                new Trader("boxer", 22, f1, null)
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
    @DisplayName("QueryDSL-[상수] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_Constant()
    {
        //WHERE
        QTrader t = QTrader.trader;

        List<Tuple> results = jpaQueryFactory
                .select(t.username, Expressions.constant("테스트"))
                .from(t)
                .fetch();

        results.forEach(r -> log.info("{}",r));

    }

    @Test
    @DisplayName("QueryDSL-[CONCAT] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_Concat()
    {
        //WHERE
        QTrader t = QTrader.trader;

        List<String> concatResults = jpaQueryFactory
                .select(t.username.concat(" (").concat(t.firm.name).concat(")"))
                .from(t)
                .fetch();

        concatResults.forEach(r -> log.info("CONCAT 테스트 -> {}", r));

    }

    @Test
    @DisplayName("QueryDSL-[SUBSTRING] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_SUBSTRING()
    {
        //WHERE
        QTrader t = QTrader.trader;

        List<Tuple> substrResults = jpaQueryFactory
                .select(t.username,t.username.substring(0, 3) )// 0~2 인덱스까지
                .from(t)
                .fetch();
        substrResults.forEach(r -> log.info("SUBSTRING(0,3) 테스트 -> {}", r));

    }

    @Test
    @DisplayName("QueryDSL-[LENGTH] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_LENGTH()
    {
        //WHERE
        QTrader t = QTrader.trader;

        List<Tuple> lengthResults = jpaQueryFactory
                .select(t.username, t.username.length())
                .from(t)
                .fetch();
        lengthResults.forEach(r -> log.info("LENGTH 테스트 -> {}", r));

    }

    @Test
    @DisplayName("QueryDSL-[UPPER, LOWER] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_UPPERLOWER()
    {
        //WHERE
        QTrader t = QTrader.trader;

        List<Tuple> traders = jpaQueryFactory
                .select(t.username.upper()
                        , t.firm.name.lower())
                .from(t)
                .fetch();
        traders.forEach(r -> log.info("UPPER, LOWER 테스트 -> {}", r));

    }

    @Test
    @DisplayName("QueryDSL-[TRIM] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_TRIM()
    {
        //WHERE
        QTrader t = QTrader.trader;

        List<String> traders = jpaQueryFactory
                .select(t.username.concat("           ").trim())
                .from(t)
                .fetch();
        traders.forEach(r -> log.info("TRIM 테스트 -> |{}|", r));

    }

    @Test
    @DisplayName("QueryDSL-[REPLACE] 테스트")
    @Transactional
    @Rollback(false)
    void testQuertDSL_REPLACE()
    {
        //WHERE
        QTrader t = QTrader.trader;

        List<String> traders = jpaQueryFactory
                .select(Expressions.stringTemplate(
                        "replace({0}, {1}, {2})",
                        t.username, // 원본 컬럼
                        "a",        // 바꿀 문자열
                        "에이"      // 대체할 문자열
                ))
                .from(t)
                .fetch();

        traders.forEach(r -> log.info("REPLACE 테스트 -> {}", r));

    }

}
